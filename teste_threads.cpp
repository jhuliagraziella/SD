#include <bits/stdc++.h>
#include <pthread.h>

using namespace std;

int atu;              // identificador da thread q "deveria" rodar agora, de acordo com o proposto
int ult;              // posicao do ultimo caractere da string q eh maiusculo
int q_thread;         // quantidade de threads
string msg;           // mensagem do usuario
pthread_mutex_t lock; // lock pra controle de concorrencia

void* run (void* aux) {
  long id = (long) aux;

  int i = 0;
  while(msg[ult] >= 'a'){
    pthread_mutex_lock (&lock);
    
    if(id == atu){
      cout << "Thread " << id << ":\n";
      cout << "Mensagem recebida: " << msg << "\n";
      for(;i<msg.size();i++){
        if(msg[i] >= 'a' && msg[i] <= 'z'){
          msg[i] -= 32;
          break;
        }
      }
      cout << "Nova mensagem: " << msg << "\n\n";
      sleep(1);
      atu = (atu + 1) % q_thread;
    }

    pthread_mutex_unlock (&lock);
  }
  return NULL;
}

int main () {
  cout << "Digite a quantidade de threads: ";
  cin >> q_thread;

  printf("Digite uma mensagem: ");
  cin >> msg;

  pthread_t* threads = (pthread_t*) malloc (q_thread* sizeof (pthread_t)); // aloca memoria p/ as threads
  pthread_mutex_init (&lock, NULL); // inicializa a lock    
    
  ult = 0;
  for(int i=0;i<msg.size();i++)
    if(msg[i] >= 'a' && msg[i] <= 'z')
      ult = i;
  
  atu = 0;
  for (long thread=0;thread<q_thread;thread++)
    pthread_create (&threads[thread], NULL, run, (void*) thread);

  for (long thread=0;thread<q_thread;thread++)
    pthread_join (threads [thread], NULL) ;

  pthread_mutex_destroy (&lock);    // destroi a lock
  free (threads);                   // libera a memoria alocado p/ as threads
  return 0;
}

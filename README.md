# SD

Tarefa proposta:

"Anel Multithread

Usando uma linguagem de alto-nível como C/C++/Java, escrever um programa que crie 30 threads e faça com que a mensagem circule entre os mesmos.
A mensagem é uma string aleatória de pelo menos 80 caracteres.
A cada vez que um thread recebe a mensagem ele a imprime, modifica o primeiro caractere minúsculo para maiúsculo, caso exista, dorme por 1 segundo, e repassa a  mensagem.
Quando todos os caracteres forem maiúsculos, o processo repassa a mensagem e então termina.
Antes de terminar, o processo deve imprimir a mensagem resultante."

A tarefa foi feita na linguagem C++, usando mutex para controle de concorrência. Há uma versão onde a ordem de execução das threads é cíclica e outra onde é aleatória.

Obs: em C++ a função sleep() recebe como paramentro a quantidade de segundos e não de milisegundos, por isso sleep(1) ao invés de sleep(1000), como estava no exemplo.

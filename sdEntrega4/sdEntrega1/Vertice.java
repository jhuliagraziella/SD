/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package sdEntrega1;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-07-29")
public class Vertice implements org.apache.thrift.TBase<Vertice, Vertice._Fields>, java.io.Serializable, Cloneable, Comparable<Vertice> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Vertice");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField COR_FIELD_DESC = new org.apache.thrift.protocol.TField("cor", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField PESO_FIELD_DESC = new org.apache.thrift.protocol.TField("peso", org.apache.thrift.protocol.TType.DOUBLE, (short)3);
  private static final org.apache.thrift.protocol.TField DESCRICAO_FIELD_DESC = new org.apache.thrift.protocol.TField("descricao", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField TIPO_FIELD_DESC = new org.apache.thrift.protocol.TField("tipo", org.apache.thrift.protocol.TType.I32, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new VerticeStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new VerticeTupleSchemeFactory();

  public long id; // required
  public int cor; // required
  public double peso; // required
  public java.lang.String descricao; // required
  public int tipo; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    COR((short)2, "cor"),
    PESO((short)3, "peso"),
    DESCRICAO((short)4, "descricao"),
    TIPO((short)5, "tipo");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // COR
          return COR;
        case 3: // PESO
          return PESO;
        case 4: // DESCRICAO
          return DESCRICAO;
        case 5: // TIPO
          return TIPO;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ID_ISSET_ID = 0;
  private static final int __COR_ISSET_ID = 1;
  private static final int __PESO_ISSET_ID = 2;
  private static final int __TIPO_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.COR, new org.apache.thrift.meta_data.FieldMetaData("cor", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.PESO, new org.apache.thrift.meta_data.FieldMetaData("peso", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.DESCRICAO, new org.apache.thrift.meta_data.FieldMetaData("descricao", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TIPO, new org.apache.thrift.meta_data.FieldMetaData("tipo", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Vertice.class, metaDataMap);
  }

  public Vertice() {
  }

  public Vertice(
    long id,
    int cor,
    double peso,
    java.lang.String descricao,
    int tipo)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.cor = cor;
    setCorIsSet(true);
    this.peso = peso;
    setPesoIsSet(true);
    this.descricao = descricao;
    this.tipo = tipo;
    setTipoIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Vertice(Vertice other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    this.cor = other.cor;
    this.peso = other.peso;
    if (other.isSetDescricao()) {
      this.descricao = other.descricao;
    }
    this.tipo = other.tipo;
  }

  public Vertice deepCopy() {
    return new Vertice(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    setCorIsSet(false);
    this.cor = 0;
    setPesoIsSet(false);
    this.peso = 0.0;
    this.descricao = null;
    setTipoIsSet(false);
    this.tipo = 0;
  }

  public java.lang.String getInformacoes(){
      java.lang.String s = "";
      s += "ID: " + java.lang.String.valueOf(this.id) + "\n";
      // s += "Cor: " + java.lang.String.valueOf(this.cor) + "\n";
      // s += "Peso: " + java.lang.String.valueOf(this.peso) + "\n";
      s += "Nome/Descricao: " + this.descricao + "\n";
  
      return s;
  }

  public long getId() {
    return this.id;
  }

  public Vertice setId(long id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public int getCor() {
    return this.cor;
  }

  public Vertice setCor(int cor) {
    this.cor = cor;
    setCorIsSet(true);
    return this;
  }

  public void unsetCor() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __COR_ISSET_ID);
  }

  /** Returns true if field cor is set (has been assigned a value) and false otherwise */
  public boolean isSetCor() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __COR_ISSET_ID);
  }

  public void setCorIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __COR_ISSET_ID, value);
  }

  public double getPeso() {
    return this.peso;
  }

  public Vertice setPeso(double peso) {
    this.peso = peso;
    setPesoIsSet(true);
    return this;
  }

  public void unsetPeso() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __PESO_ISSET_ID);
  }

  /** Returns true if field peso is set (has been assigned a value) and false otherwise */
  public boolean isSetPeso() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __PESO_ISSET_ID);
  }

  public void setPesoIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __PESO_ISSET_ID, value);
  }

  public java.lang.String getDescricao() {
    return this.descricao;
  }

  public Vertice setDescricao(java.lang.String descricao) {
    this.descricao = descricao;
    return this;
  }

  public void unsetDescricao() {
    this.descricao = null;
  }

  /** Returns true if field descricao is set (has been assigned a value) and false otherwise */
  public boolean isSetDescricao() {
    return this.descricao != null;
  }

  public void setDescricaoIsSet(boolean value) {
    if (!value) {
      this.descricao = null;
    }
  }

  public int getTipo() {
    return this.tipo;
  }

  public Vertice setTipo(int tipo) {
    this.tipo = tipo;
    setTipoIsSet(true);
    return this;
  }

  public void unsetTipo() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TIPO_ISSET_ID);
  }

  /** Returns true if field tipo is set (has been assigned a value) and false otherwise */
  public boolean isSetTipo() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TIPO_ISSET_ID);
  }

  public void setTipoIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TIPO_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((java.lang.Long)value);
      }
      break;

    case COR:
      if (value == null) {
        unsetCor();
      } else {
        setCor((java.lang.Integer)value);
      }
      break;

    case PESO:
      if (value == null) {
        unsetPeso();
      } else {
        setPeso((java.lang.Double)value);
      }
      break;

    case DESCRICAO:
      if (value == null) {
        unsetDescricao();
      } else {
        setDescricao((java.lang.String)value);
      }
      break;

    case TIPO:
      if (value == null) {
        unsetTipo();
      } else {
        setTipo((java.lang.Integer)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case COR:
      return getCor();

    case PESO:
      return getPeso();

    case DESCRICAO:
      return getDescricao();

    case TIPO:
      return getTipo();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case COR:
      return isSetCor();
    case PESO:
      return isSetPeso();
    case DESCRICAO:
      return isSetDescricao();
    case TIPO:
      return isSetTipo();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Vertice)
      return this.equals((Vertice)that);
    return false;
  }

  // public boolean equals(Vertice that) {
  //   if (that == null)
  //     return false;
  //   if (this == that)
  //     return true;

  //   boolean this_present_id = true;
  //   boolean that_present_id = true;
  //   if (this_present_id || that_present_id) {
  //     if (!(this_present_id && that_present_id))
  //       return false;
  //     if (this.id != that.id)
  //       return false;
  //   }

  //   boolean this_present_cor = true;
  //   boolean that_present_cor = true;
  //   if (this_present_cor || that_present_cor) {
  //     if (!(this_present_cor && that_present_cor))
  //       return false;
  //     if (this.cor != that.cor)
  //       return false;
  //   }

  //   boolean this_present_peso = true;
  //   boolean that_present_peso = true;
  //   if (this_present_peso || that_present_peso) {
  //     if (!(this_present_peso && that_present_peso))
  //       return false;
  //     if (this.peso != that.peso)
  //       return false;
  //   }

  //   boolean this_present_descricao = true && this.isSetDescricao();
  //   boolean that_present_descricao = true && that.isSetDescricao();
  //   if (this_present_descricao || that_present_descricao) {
  //     if (!(this_present_descricao && that_present_descricao))
  //       return false;
  //     if (!this.descricao.equals(that.descricao))
  //       return false;
  //   }

  //   boolean this_present_tipo = true;
  //   boolean that_present_tipo = true;
  //   if (this_present_tipo || that_present_tipo) {
  //     if (!(this_present_tipo && that_present_tipo))
  //       return false;
  //     if (this.tipo != that.tipo)
  //       return false;
  //   }

  //   return true;
  // }

  public boolean equals(Vertice that) {
    if (that == null)
      return false;

    return this.id == that.getId();
  }

  // @Override
  // public int hashCode() {
  //   int hashCode = 1;

  //   hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(id);

  //   hashCode = hashCode * 8191 + cor;

  //   hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(peso);

  //   hashCode = hashCode * 8191 + ((isSetDescricao()) ? 131071 : 524287);
  //   if (isSetDescricao())
  //     hashCode = hashCode * 8191 + descricao.hashCode();

  //   hashCode = hashCode * 8191 + tipo;

  //   return hashCode;
  // }

  @Override
  public int hashCode() {
    return (int) this.id;
  }

  @Override
  public int compareTo(Vertice other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetCor()).compareTo(other.isSetCor());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCor()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.cor, other.cor);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPeso()).compareTo(other.isSetPeso());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPeso()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.peso, other.peso);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDescricao()).compareTo(other.isSetDescricao());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDescricao()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.descricao, other.descricao);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTipo()).compareTo(other.isSetTipo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTipo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tipo, other.tipo);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Vertice(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("cor:");
    sb.append(this.cor);
    first = false;
    if (!first) sb.append(", ");
    sb.append("peso:");
    sb.append(this.peso);
    first = false;
    if (!first) sb.append(", ");
    sb.append("descricao:");
    if (this.descricao == null) {
      sb.append("null");
    } else {
      sb.append(this.descricao);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("tipo:");
    sb.append(this.tipo);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class VerticeStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public VerticeStandardScheme getScheme() {
      return new VerticeStandardScheme();
    }
  }

  private static class VerticeStandardScheme extends org.apache.thrift.scheme.StandardScheme<Vertice> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Vertice struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.id = iprot.readI64();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // COR
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.cor = iprot.readI32();
              struct.setCorIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PESO
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.peso = iprot.readDouble();
              struct.setPesoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // DESCRICAO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.descricao = iprot.readString();
              struct.setDescricaoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // TIPO
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.tipo = iprot.readI32();
              struct.setTipoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Vertice struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI64(struct.id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(COR_FIELD_DESC);
      oprot.writeI32(struct.cor);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(PESO_FIELD_DESC);
      oprot.writeDouble(struct.peso);
      oprot.writeFieldEnd();
      if (struct.descricao != null) {
        oprot.writeFieldBegin(DESCRICAO_FIELD_DESC);
        oprot.writeString(struct.descricao);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIPO_FIELD_DESC);
      oprot.writeI32(struct.tipo);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class VerticeTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public VerticeTupleScheme getScheme() {
      return new VerticeTupleScheme();
    }
  }

  private static class VerticeTupleScheme extends org.apache.thrift.scheme.TupleScheme<Vertice> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Vertice struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetCor()) {
        optionals.set(1);
      }
      if (struct.isSetPeso()) {
        optionals.set(2);
      }
      if (struct.isSetDescricao()) {
        optionals.set(3);
      }
      if (struct.isSetTipo()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetId()) {
        oprot.writeI64(struct.id);
      }
      if (struct.isSetCor()) {
        oprot.writeI32(struct.cor);
      }
      if (struct.isSetPeso()) {
        oprot.writeDouble(struct.peso);
      }
      if (struct.isSetDescricao()) {
        oprot.writeString(struct.descricao);
      }
      if (struct.isSetTipo()) {
        oprot.writeI32(struct.tipo);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Vertice struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.id = iprot.readI64();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.cor = iprot.readI32();
        struct.setCorIsSet(true);
      }
      if (incoming.get(2)) {
        struct.peso = iprot.readDouble();
        struct.setPesoIsSet(true);
      }
      if (incoming.get(3)) {
        struct.descricao = iprot.readString();
        struct.setDescricaoIsSet(true);
      }
      if (incoming.get(4)) {
        struct.tipo = iprot.readI32();
        struct.setTipoIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}


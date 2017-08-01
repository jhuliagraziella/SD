package thrift;

/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-07-29")
public class Aresta implements org.apache.thrift.TBase<Aresta, Aresta._Fields>, java.io.Serializable, Cloneable, Comparable<Aresta> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("thrift.Aresta");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField VA_FIELD_DESC = new org.apache.thrift.protocol.TField("va", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField VB_FIELD_DESC = new org.apache.thrift.protocol.TField("vb", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField PESO_FIELD_DESC = new org.apache.thrift.protocol.TField("peso", org.apache.thrift.protocol.TType.DOUBLE, (short)4);
  private static final org.apache.thrift.protocol.TField BIDIRECIONAL_FIELD_DESC = new org.apache.thrift.protocol.TField("bidirecional", org.apache.thrift.protocol.TType.BOOL, (short)5);
  private static final org.apache.thrift.protocol.TField DESCRICAO_FIELD_DESC = new org.apache.thrift.protocol.TField("descricao", org.apache.thrift.protocol.TType.STRING, (short)6);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ArestaStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ArestaTupleSchemeFactory();

  public long id; // required
  public long va; // required
  public long vb; // required
  public double peso; // required
  public boolean bidirecional; // required
  public String descricao; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    VA((short)2, "va"),
    VB((short)3, "vb"),
    PESO((short)4, "peso"),
    BIDIRECIONAL((short)5, "bidirecional"),
    DESCRICAO((short)6, "descricao");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

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
        case 2: // VA
          return VA;
        case 3: // VB
          return VB;
        case 4: // PESO
          return PESO;
        case 5: // BIDIRECIONAL
          return BIDIRECIONAL;
        case 6: // DESCRICAO
          return DESCRICAO;
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
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ID_ISSET_ID = 0;
  private static final int __VA_ISSET_ID = 1;
  private static final int __VB_ISSET_ID = 2;
  private static final int __PESO_ISSET_ID = 3;
  private static final int __BIDIRECIONAL_ISSET_ID = 4;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.VA, new org.apache.thrift.meta_data.FieldMetaData("va", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.VB, new org.apache.thrift.meta_data.FieldMetaData("vb", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.PESO, new org.apache.thrift.meta_data.FieldMetaData("peso", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.BIDIRECIONAL, new org.apache.thrift.meta_data.FieldMetaData("bidirecional", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.DESCRICAO, new org.apache.thrift.meta_data.FieldMetaData("descricao", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Aresta.class, metaDataMap);
  }

  public Aresta() {
  }

  public Aresta(
    long id,
    long va,
    long vb,
    double peso,
    boolean bidirecional,
    String descricao)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.va = va;
    setVaIsSet(true);
    this.vb = vb;
    setVbIsSet(true);
    this.peso = peso;
    setPesoIsSet(true);
    this.bidirecional = bidirecional;
    setBidirecionalIsSet(true);
    this.descricao = descricao;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Aresta(Aresta other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    this.va = other.va;
    this.vb = other.vb;
    this.peso = other.peso;
    this.bidirecional = other.bidirecional;
    if (other.isSetDescricao()) {
      this.descricao = other.descricao;
    }
  }

  public Aresta deepCopy() {
    return new Aresta(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    setVaIsSet(false);
    this.va = 0;
    setVbIsSet(false);
    this.vb = 0;
    setPesoIsSet(false);
    this.peso = 0.0;
    setBidirecionalIsSet(false);
    this.bidirecional = false;
    this.descricao = null;
  }

  public String getInformacoes(){
      String s = "";
      s += "Id: " + String.valueOf(this.id) + "\n";
      s += "CPF Cliente: " + String.valueOf(this.va) + "\n";
      s += "IMDB ID Filme: " + String.valueOf(this.vb) + "\n";
      s += "Avaliacao: " + String.valueOf(this.peso) + "\n";
      // s += "Bidirecional: " + (this.bidirecional ? "sim" : "nao") + "\n";
      // s += "Descricao: " + this.descricao + "\n";

      return s;
  }

  public long getId() {
    return this.id;
  }

  public Aresta setId(long id) {
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

  public long getVa() {
    return this.va;
  }

  public Aresta setVa(long va) {
    this.va = va;
    setVaIsSet(true);
    return this;
  }

  public void unsetVa() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __VA_ISSET_ID);
  }

  /** Returns true if field va is set (has been assigned a value) and false otherwise */
  public boolean isSetVa() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __VA_ISSET_ID);
  }

  public void setVaIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __VA_ISSET_ID, value);
  }

  public long getVb() {
    return this.vb;
  }

  public Aresta setVb(long vb) {
    this.vb = vb;
    setVbIsSet(true);
    return this;
  }

  public void unsetVb() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __VB_ISSET_ID);
  }

  /** Returns true if field vb is set (has been assigned a value) and false otherwise */
  public boolean isSetVb() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __VB_ISSET_ID);
  }

  public void setVbIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __VB_ISSET_ID, value);
  }

  public double getPeso() {
    return this.peso;
  }

  public Aresta setPeso(double peso) {
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

  public boolean isBidirecional() {
    return this.bidirecional;
  }

  public Aresta setBidirecional(boolean bidirecional) {
    this.bidirecional = bidirecional;
    setBidirecionalIsSet(true);
    return this;
  }

  public void unsetBidirecional() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __BIDIRECIONAL_ISSET_ID);
  }

  /** Returns true if field bidirecional is set (has been assigned a value) and false otherwise */
  public boolean isSetBidirecional() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __BIDIRECIONAL_ISSET_ID);
  }

  public void setBidirecionalIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __BIDIRECIONAL_ISSET_ID, value);
  }

  public String getDescricao() {
    return this.descricao;
  }

  public Aresta setDescricao(String descricao) {
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

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Long)value);
      }
      break;

    case VA:
      if (value == null) {
        unsetVa();
      } else {
        setVa((Long)value);
      }
      break;

    case VB:
      if (value == null) {
        unsetVb();
      } else {
        setVb((Long)value);
      }
      break;

    case PESO:
      if (value == null) {
        unsetPeso();
      } else {
        setPeso((Double)value);
      }
      break;

    case BIDIRECIONAL:
      if (value == null) {
        unsetBidirecional();
      } else {
        setBidirecional((Boolean)value);
      }
      break;

    case DESCRICAO:
      if (value == null) {
        unsetDescricao();
      } else {
        setDescricao((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case VA:
      return getVa();

    case VB:
      return getVb();

    case PESO:
      return getPeso();

    case BIDIRECIONAL:
      return isBidirecional();

    case DESCRICAO:
      return getDescricao();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case VA:
      return isSetVa();
    case VB:
      return isSetVb();
    case PESO:
      return isSetPeso();
    case BIDIRECIONAL:
      return isSetBidirecional();
    case DESCRICAO:
      return isSetDescricao();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Aresta)
      return this.equals((Aresta)that);
    return false;
  }

  // public boolean equals(thrift.Aresta that) {
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

  //   boolean this_present_va = true;
  //   boolean that_present_va = true;
  //   if (this_present_va || that_present_va) {
  //     if (!(this_present_va && that_present_va))
  //       return false;
  //     if (this.va != that.va)
  //       return false;
  //   }

  //   boolean this_present_vb = true;
  //   boolean that_present_vb = true;
  //   if (this_present_vb || that_present_vb) {
  //     if (!(this_present_vb && that_present_vb))
  //       return false;
  //     if (this.vb != that.vb)
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

  //   boolean this_present_bidirecional = true;
  //   boolean that_present_bidirecional = true;
  //   if (this_present_bidirecional || that_present_bidirecional) {
  //     if (!(this_present_bidirecional && that_present_bidirecional))
  //       return false;
  //     if (this.bidirecional != that.bidirecional)
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

  //   return true;
  // }

  public boolean equals(Aresta that) {
    if (that == null)
      return false;
    return (this.id == that.getId());
  }

  // @Override
  // public int hashCode() {
  //   int hashCode = 1;

  //   hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(id);

  //   hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(va);

  //   hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(vb);

  //   hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(peso);

  //   hashCode = hashCode * 8191 + ((bidirecional) ? 131071 : 524287);

  //   hashCode = hashCode * 8191 + ((isSetDescricao()) ? 131071 : 524287);
  //   if (isSetDescricao())
  //     hashCode = hashCode * 8191 + descricao.hashCode();

  //   return hashCode;
  // }

  @Override
  public int hashCode() {
    return (int) this.id;
  }

  @Override
  public int compareTo(Aresta other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVa()).compareTo(other.isSetVa());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVa()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.va, other.va);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVb()).compareTo(other.isSetVb());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVb()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.vb, other.vb);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPeso()).compareTo(other.isSetPeso());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPeso()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.peso, other.peso);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBidirecional()).compareTo(other.isSetBidirecional());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBidirecional()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.bidirecional, other.bidirecional);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDescricao()).compareTo(other.isSetDescricao());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDescricao()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.descricao, other.descricao);
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
  public String toString() {
    StringBuilder sb = new StringBuilder("thrift.Aresta(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("va:");
    sb.append(this.va);
    first = false;
    if (!first) sb.append(", ");
    sb.append("vb:");
    sb.append(this.vb);
    first = false;
    if (!first) sb.append(", ");
    sb.append("peso:");
    sb.append(this.peso);
    first = false;
    if (!first) sb.append(", ");
    sb.append("bidirecional:");
    sb.append(this.bidirecional);
    first = false;
    if (!first) sb.append(", ");
    sb.append("descricao:");
    if (this.descricao == null) {
      sb.append("null");
    } else {
      sb.append(this.descricao);
    }
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

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ArestaStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ArestaStandardScheme getScheme() {
      return new ArestaStandardScheme();
    }
  }

  private static class ArestaStandardScheme extends org.apache.thrift.scheme.StandardScheme<Aresta> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Aresta struct) throws org.apache.thrift.TException {
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
          case 2: // VA
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.va = iprot.readI64();
              struct.setVaIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // VB
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.vb = iprot.readI64();
              struct.setVbIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PESO
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.peso = iprot.readDouble();
              struct.setPesoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // BIDIRECIONAL
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.bidirecional = iprot.readBool();
              struct.setBidirecionalIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // DESCRICAO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.descricao = iprot.readString();
              struct.setDescricaoIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Aresta struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI64(struct.id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(VA_FIELD_DESC);
      oprot.writeI64(struct.va);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(VB_FIELD_DESC);
      oprot.writeI64(struct.vb);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(PESO_FIELD_DESC);
      oprot.writeDouble(struct.peso);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(BIDIRECIONAL_FIELD_DESC);
      oprot.writeBool(struct.bidirecional);
      oprot.writeFieldEnd();
      if (struct.descricao != null) {
        oprot.writeFieldBegin(DESCRICAO_FIELD_DESC);
        oprot.writeString(struct.descricao);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ArestaTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ArestaTupleScheme getScheme() {
      return new ArestaTupleScheme();
    }
  }

  private static class ArestaTupleScheme extends org.apache.thrift.scheme.TupleScheme<Aresta> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Aresta struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetVa()) {
        optionals.set(1);
      }
      if (struct.isSetVb()) {
        optionals.set(2);
      }
      if (struct.isSetPeso()) {
        optionals.set(3);
      }
      if (struct.isSetBidirecional()) {
        optionals.set(4);
      }
      if (struct.isSetDescricao()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetId()) {
        oprot.writeI64(struct.id);
      }
      if (struct.isSetVa()) {
        oprot.writeI64(struct.va);
      }
      if (struct.isSetVb()) {
        oprot.writeI64(struct.vb);
      }
      if (struct.isSetPeso()) {
        oprot.writeDouble(struct.peso);
      }
      if (struct.isSetBidirecional()) {
        oprot.writeBool(struct.bidirecional);
      }
      if (struct.isSetDescricao()) {
        oprot.writeString(struct.descricao);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Aresta struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.id = iprot.readI64();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.va = iprot.readI64();
        struct.setVaIsSet(true);
      }
      if (incoming.get(2)) {
        struct.vb = iprot.readI64();
        struct.setVbIsSet(true);
      }
      if (incoming.get(3)) {
        struct.peso = iprot.readDouble();
        struct.setPesoIsSet(true);
      }
      if (incoming.get(4)) {
        struct.bidirecional = iprot.readBool();
        struct.setBidirecionalIsSet(true);
      }
      if (incoming.get(5)) {
        struct.descricao = iprot.readString();
        struct.setDescricaoIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}


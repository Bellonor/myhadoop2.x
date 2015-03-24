/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.jamesfen.hbasethrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TTimeRange implements org.apache.thrift.TBase<TTimeRange, TTimeRange._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TTimeRange");

  private static final org.apache.thrift.protocol.TField MIN_STAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("minStamp", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField MAX_STAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("maxStamp", org.apache.thrift.protocol.TType.I64, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TTimeRangeStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TTimeRangeTupleSchemeFactory());
  }

  public long minStamp; // required
  public long maxStamp; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    MIN_STAMP((short)1, "minStamp"),
    MAX_STAMP((short)2, "maxStamp");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // MIN_STAMP
          return MIN_STAMP;
        case 2: // MAX_STAMP
          return MAX_STAMP;
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
  private static final int __MINSTAMP_ISSET_ID = 0;
  private static final int __MAXSTAMP_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.MIN_STAMP, new org.apache.thrift.meta_data.FieldMetaData("minStamp", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.MAX_STAMP, new org.apache.thrift.meta_data.FieldMetaData("maxStamp", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TTimeRange.class, metaDataMap);
  }

  public TTimeRange() {
  }

  public TTimeRange(
    long minStamp,
    long maxStamp)
  {
    this();
    this.minStamp = minStamp;
    setMinStampIsSet(true);
    this.maxStamp = maxStamp;
    setMaxStampIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TTimeRange(TTimeRange other) {
    __isset_bitfield = other.__isset_bitfield;
    this.minStamp = other.minStamp;
    this.maxStamp = other.maxStamp;
  }

  public TTimeRange deepCopy() {
    return new TTimeRange(this);
  }

  @Override
  public void clear() {
    setMinStampIsSet(false);
    this.minStamp = 0;
    setMaxStampIsSet(false);
    this.maxStamp = 0;
  }

  public long getMinStamp() {
    return this.minStamp;
  }

  public TTimeRange setMinStamp(long minStamp) {
    this.minStamp = minStamp;
    setMinStampIsSet(true);
    return this;
  }

  public void unsetMinStamp() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __MINSTAMP_ISSET_ID);
  }

  /** Returns true if field minStamp is set (has been assigned a value) and false otherwise */
  public boolean isSetMinStamp() {
    return EncodingUtils.testBit(__isset_bitfield, __MINSTAMP_ISSET_ID);
  }

  public void setMinStampIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __MINSTAMP_ISSET_ID, value);
  }

  public long getMaxStamp() {
    return this.maxStamp;
  }

  public TTimeRange setMaxStamp(long maxStamp) {
    this.maxStamp = maxStamp;
    setMaxStampIsSet(true);
    return this;
  }

  public void unsetMaxStamp() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __MAXSTAMP_ISSET_ID);
  }

  /** Returns true if field maxStamp is set (has been assigned a value) and false otherwise */
  public boolean isSetMaxStamp() {
    return EncodingUtils.testBit(__isset_bitfield, __MAXSTAMP_ISSET_ID);
  }

  public void setMaxStampIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __MAXSTAMP_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case MIN_STAMP:
      if (value == null) {
        unsetMinStamp();
      } else {
        setMinStamp((Long)value);
      }
      break;

    case MAX_STAMP:
      if (value == null) {
        unsetMaxStamp();
      } else {
        setMaxStamp((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case MIN_STAMP:
      return Long.valueOf(getMinStamp());

    case MAX_STAMP:
      return Long.valueOf(getMaxStamp());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case MIN_STAMP:
      return isSetMinStamp();
    case MAX_STAMP:
      return isSetMaxStamp();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TTimeRange)
      return this.equals((TTimeRange)that);
    return false;
  }

  public boolean equals(TTimeRange that) {
    if (that == null)
      return false;

    boolean this_present_minStamp = true;
    boolean that_present_minStamp = true;
    if (this_present_minStamp || that_present_minStamp) {
      if (!(this_present_minStamp && that_present_minStamp))
        return false;
      if (this.minStamp != that.minStamp)
        return false;
    }

    boolean this_present_maxStamp = true;
    boolean that_present_maxStamp = true;
    if (this_present_maxStamp || that_present_maxStamp) {
      if (!(this_present_maxStamp && that_present_maxStamp))
        return false;
      if (this.maxStamp != that.maxStamp)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(TTimeRange other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    TTimeRange typedOther = (TTimeRange)other;

    lastComparison = Boolean.valueOf(isSetMinStamp()).compareTo(typedOther.isSetMinStamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMinStamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.minStamp, typedOther.minStamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMaxStamp()).compareTo(typedOther.isSetMaxStamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMaxStamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.maxStamp, typedOther.maxStamp);
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
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TTimeRange(");
    boolean first = true;

    sb.append("minStamp:");
    sb.append(this.minStamp);
    first = false;
    if (!first) sb.append(", ");
    sb.append("maxStamp:");
    sb.append(this.maxStamp);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'minStamp' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'maxStamp' because it's a primitive and you chose the non-beans generator.
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

  private static class TTimeRangeStandardSchemeFactory implements SchemeFactory {
    public TTimeRangeStandardScheme getScheme() {
      return new TTimeRangeStandardScheme();
    }
  }

  private static class TTimeRangeStandardScheme extends StandardScheme<TTimeRange> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TTimeRange struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // MIN_STAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.minStamp = iprot.readI64();
              struct.setMinStampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // MAX_STAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.maxStamp = iprot.readI64();
              struct.setMaxStampIsSet(true);
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
      if (!struct.isSetMinStamp()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'minStamp' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetMaxStamp()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'maxStamp' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TTimeRange struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(MIN_STAMP_FIELD_DESC);
      oprot.writeI64(struct.minStamp);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(MAX_STAMP_FIELD_DESC);
      oprot.writeI64(struct.maxStamp);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TTimeRangeTupleSchemeFactory implements SchemeFactory {
    public TTimeRangeTupleScheme getScheme() {
      return new TTimeRangeTupleScheme();
    }
  }

  private static class TTimeRangeTupleScheme extends TupleScheme<TTimeRange> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TTimeRange struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI64(struct.minStamp);
      oprot.writeI64(struct.maxStamp);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TTimeRange struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.minStamp = iprot.readI64();
      struct.setMinStampIsSet(true);
      struct.maxStamp = iprot.readI64();
      struct.setMaxStampIsSet(true);
    }
  }

}


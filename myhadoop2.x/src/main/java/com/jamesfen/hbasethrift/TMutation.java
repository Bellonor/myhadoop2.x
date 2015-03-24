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

/**
 * Atomic mutation for the specified row. It can be either Put or Delete.
 */
public class TMutation extends org.apache.thrift.TUnion<TMutation, TMutation._Fields> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TMutation");
  private static final org.apache.thrift.protocol.TField PUT_FIELD_DESC = new org.apache.thrift.protocol.TField("put", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField DELETE_SINGLE_FIELD_DESC = new org.apache.thrift.protocol.TField("deleteSingle", org.apache.thrift.protocol.TType.STRUCT, (short)2);

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PUT((short)1, "put"),
    DELETE_SINGLE((short)2, "deleteSingle");

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
        case 1: // PUT
          return PUT;
        case 2: // DELETE_SINGLE
          return DELETE_SINGLE;
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

  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PUT, new org.apache.thrift.meta_data.FieldMetaData("put", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TPut.class)));
    tmpMap.put(_Fields.DELETE_SINGLE, new org.apache.thrift.meta_data.FieldMetaData("deleteSingle", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TDelete.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TMutation.class, metaDataMap);
  }

  public TMutation() {
    super();
  }

  public TMutation(_Fields setField, Object value) {
    super(setField, value);
  }

  public TMutation(TMutation other) {
    super(other);
  }
  public TMutation deepCopy() {
    return new TMutation(this);
  }

  public static TMutation put(TPut value) {
    TMutation x = new TMutation();
    x.setPut(value);
    return x;
  }

  public static TMutation deleteSingle(TDelete value) {
    TMutation x = new TMutation();
    x.setDeleteSingle(value);
    return x;
  }


  @Override
  protected void checkType(_Fields setField, Object value) throws ClassCastException {
    switch (setField) {
      case PUT:
        if (value instanceof TPut) {
          break;
        }
        throw new ClassCastException("Was expecting value of type TPut for field 'put', but got " + value.getClass().getSimpleName());
      case DELETE_SINGLE:
        if (value instanceof TDelete) {
          break;
        }
        throw new ClassCastException("Was expecting value of type TDelete for field 'deleteSingle', but got " + value.getClass().getSimpleName());
      default:
        throw new IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected Object standardSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TField field) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(field.id);
    if (setField != null) {
      switch (setField) {
        case PUT:
          if (field.type == PUT_FIELD_DESC.type) {
            TPut put;
            put = new TPut();
            put.read(iprot);
            return put;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case DELETE_SINGLE:
          if (field.type == DELETE_SINGLE_FIELD_DESC.type) {
            TDelete deleteSingle;
            deleteSingle = new TDelete();
            deleteSingle.read(iprot);
            return deleteSingle;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        default:
          throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      return null;
    }
  }

  @Override
  protected void standardSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case PUT:
        TPut put = (TPut)value_;
        put.write(oprot);
        return;
      case DELETE_SINGLE:
        TDelete deleteSingle = (TDelete)value_;
        deleteSingle.write(oprot);
        return;
      default:
        throw new IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected Object tupleSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, short fieldID) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(fieldID);
    if (setField != null) {
      switch (setField) {
        case PUT:
          TPut put;
          put = new TPut();
          put.read(iprot);
          return put;
        case DELETE_SINGLE:
          TDelete deleteSingle;
          deleteSingle = new TDelete();
          deleteSingle.read(iprot);
          return deleteSingle;
        default:
          throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      throw new TProtocolException("Couldn't find a field with field id " + fieldID);
    }
  }

  @Override
  protected void tupleSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case PUT:
        TPut put = (TPut)value_;
        put.write(oprot);
        return;
      case DELETE_SINGLE:
        TDelete deleteSingle = (TDelete)value_;
        deleteSingle.write(oprot);
        return;
      default:
        throw new IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TField getFieldDesc(_Fields setField) {
    switch (setField) {
      case PUT:
        return PUT_FIELD_DESC;
      case DELETE_SINGLE:
        return DELETE_SINGLE_FIELD_DESC;
      default:
        throw new IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TStruct getStructDesc() {
    return STRUCT_DESC;
  }

  @Override
  protected _Fields enumForId(short id) {
    return _Fields.findByThriftIdOrThrow(id);
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }


  public TPut getPut() {
    if (getSetField() == _Fields.PUT) {
      return (TPut)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'put' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setPut(TPut value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.PUT;
    value_ = value;
  }

  public TDelete getDeleteSingle() {
    if (getSetField() == _Fields.DELETE_SINGLE) {
      return (TDelete)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'deleteSingle' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setDeleteSingle(TDelete value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.DELETE_SINGLE;
    value_ = value;
  }

  public boolean isSetPut() {
    return setField_ == _Fields.PUT;
  }


  public boolean isSetDeleteSingle() {
    return setField_ == _Fields.DELETE_SINGLE;
  }


  public boolean equals(Object other) {
    if (other instanceof TMutation) {
      return equals((TMutation)other);
    } else {
      return false;
    }
  }

  public boolean equals(TMutation other) {
    return other != null && getSetField() == other.getSetField() && getFieldValue().equals(other.getFieldValue());
  }

  @Override
  public int compareTo(TMutation other) {
    int lastComparison = org.apache.thrift.TBaseHelper.compareTo(getSetField(), other.getSetField());
    if (lastComparison == 0) {
      return org.apache.thrift.TBaseHelper.compareTo(getFieldValue(), other.getFieldValue());
    }
    return lastComparison;
  }


  /**
   * If you'd like this to perform more respectably, use the hashcode generator option.
   */
  @Override
  public int hashCode() {
    return 0;
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }


}

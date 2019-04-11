// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: WalletService.proto

package com.betpawa.wallet;

/**
 * Protobuf type {@code com.betpawa.wallet.TransactionRequest}
 */
public  final class TransactionRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.betpawa.wallet.TransactionRequest)
    TransactionRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TransactionRequest.newBuilder() to construct.
  private TransactionRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TransactionRequest() {
    userId_ = 0L;
    amount_ = 0D;
    currency_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TransactionRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            userId_ = input.readInt64();
            break;
          }
          case 17: {

            amount_ = input.readDouble();
            break;
          }
          case 24: {
            int rawValue = input.readEnum();

            currency_ = rawValue;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.betpawa.wallet.WalletServiceOuterClass.internal_static_com_betpawa_wallet_TransactionRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.betpawa.wallet.WalletServiceOuterClass.internal_static_com_betpawa_wallet_TransactionRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.betpawa.wallet.TransactionRequest.class, com.betpawa.wallet.TransactionRequest.Builder.class);
  }

  public static final int USERID_FIELD_NUMBER = 1;
  private long userId_;
  /**
   * <code>int64 userId = 1;</code>
   */
  public long getUserId() {
    return userId_;
  }

  public static final int AMOUNT_FIELD_NUMBER = 2;
  private double amount_;
  /**
   * <code>double amount = 2;</code>
   */
  public double getAmount() {
    return amount_;
  }

  public static final int CURRENCY_FIELD_NUMBER = 3;
  private int currency_;
  /**
   * <code>.com.betpawa.wallet.Currency currency = 3;</code>
   */
  public int getCurrencyValue() {
    return currency_;
  }
  /**
   * <code>.com.betpawa.wallet.Currency currency = 3;</code>
   */
  public com.betpawa.wallet.Currency getCurrency() {
    @SuppressWarnings("deprecation")
    com.betpawa.wallet.Currency result = com.betpawa.wallet.Currency.valueOf(currency_);
    return result == null ? com.betpawa.wallet.Currency.UNRECOGNIZED : result;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (userId_ != 0L) {
      output.writeInt64(1, userId_);
    }
    if (amount_ != 0D) {
      output.writeDouble(2, amount_);
    }
    if (currency_ != com.betpawa.wallet.Currency.EUR.getNumber()) {
      output.writeEnum(3, currency_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (userId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, userId_);
    }
    if (amount_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, amount_);
    }
    if (currency_ != com.betpawa.wallet.Currency.EUR.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, currency_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.betpawa.wallet.TransactionRequest)) {
      return super.equals(obj);
    }
    com.betpawa.wallet.TransactionRequest other = (com.betpawa.wallet.TransactionRequest) obj;

    boolean result = true;
    result = result && (getUserId()
        == other.getUserId());
    result = result && (
        java.lang.Double.doubleToLongBits(getAmount())
        == java.lang.Double.doubleToLongBits(
            other.getAmount()));
    result = result && currency_ == other.currency_;
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + USERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getUserId());
    hash = (37 * hash) + AMOUNT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getAmount()));
    hash = (37 * hash) + CURRENCY_FIELD_NUMBER;
    hash = (53 * hash) + currency_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.betpawa.wallet.TransactionRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.betpawa.wallet.TransactionRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.betpawa.wallet.TransactionRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.betpawa.wallet.TransactionRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.betpawa.wallet.TransactionRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.betpawa.wallet.TransactionRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.betpawa.wallet.TransactionRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.betpawa.wallet.TransactionRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.betpawa.wallet.TransactionRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.betpawa.wallet.TransactionRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.betpawa.wallet.TransactionRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.betpawa.wallet.TransactionRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.betpawa.wallet.TransactionRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.betpawa.wallet.TransactionRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.betpawa.wallet.TransactionRequest)
      com.betpawa.wallet.TransactionRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.betpawa.wallet.WalletServiceOuterClass.internal_static_com_betpawa_wallet_TransactionRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.betpawa.wallet.WalletServiceOuterClass.internal_static_com_betpawa_wallet_TransactionRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.betpawa.wallet.TransactionRequest.class, com.betpawa.wallet.TransactionRequest.Builder.class);
    }

    // Construct using com.betpawa.wallet.TransactionRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      userId_ = 0L;

      amount_ = 0D;

      currency_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.betpawa.wallet.WalletServiceOuterClass.internal_static_com_betpawa_wallet_TransactionRequest_descriptor;
    }

    @java.lang.Override
    public com.betpawa.wallet.TransactionRequest getDefaultInstanceForType() {
      return com.betpawa.wallet.TransactionRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.betpawa.wallet.TransactionRequest build() {
      com.betpawa.wallet.TransactionRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.betpawa.wallet.TransactionRequest buildPartial() {
      com.betpawa.wallet.TransactionRequest result = new com.betpawa.wallet.TransactionRequest(this);
      result.userId_ = userId_;
      result.amount_ = amount_;
      result.currency_ = currency_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.betpawa.wallet.TransactionRequest) {
        return mergeFrom((com.betpawa.wallet.TransactionRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.betpawa.wallet.TransactionRequest other) {
      if (other == com.betpawa.wallet.TransactionRequest.getDefaultInstance()) return this;
      if (other.getUserId() != 0L) {
        setUserId(other.getUserId());
      }
      if (other.getAmount() != 0D) {
        setAmount(other.getAmount());
      }
      if (other.currency_ != 0) {
        setCurrencyValue(other.getCurrencyValue());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.betpawa.wallet.TransactionRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.betpawa.wallet.TransactionRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long userId_ ;
    /**
     * <code>int64 userId = 1;</code>
     */
    public long getUserId() {
      return userId_;
    }
    /**
     * <code>int64 userId = 1;</code>
     */
    public Builder setUserId(long value) {
      
      userId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 userId = 1;</code>
     */
    public Builder clearUserId() {
      
      userId_ = 0L;
      onChanged();
      return this;
    }

    private double amount_ ;
    /**
     * <code>double amount = 2;</code>
     */
    public double getAmount() {
      return amount_;
    }
    /**
     * <code>double amount = 2;</code>
     */
    public Builder setAmount(double value) {
      
      amount_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double amount = 2;</code>
     */
    public Builder clearAmount() {
      
      amount_ = 0D;
      onChanged();
      return this;
    }

    private int currency_ = 0;
    /**
     * <code>.com.betpawa.wallet.Currency currency = 3;</code>
     */
    public int getCurrencyValue() {
      return currency_;
    }
    /**
     * <code>.com.betpawa.wallet.Currency currency = 3;</code>
     */
    public Builder setCurrencyValue(int value) {
      currency_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.com.betpawa.wallet.Currency currency = 3;</code>
     */
    public com.betpawa.wallet.Currency getCurrency() {
      @SuppressWarnings("deprecation")
      com.betpawa.wallet.Currency result = com.betpawa.wallet.Currency.valueOf(currency_);
      return result == null ? com.betpawa.wallet.Currency.UNRECOGNIZED : result;
    }
    /**
     * <code>.com.betpawa.wallet.Currency currency = 3;</code>
     */
    public Builder setCurrency(com.betpawa.wallet.Currency value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      currency_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.com.betpawa.wallet.Currency currency = 3;</code>
     */
    public Builder clearCurrency() {
      
      currency_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.betpawa.wallet.TransactionRequest)
  }

  // @@protoc_insertion_point(class_scope:com.betpawa.wallet.TransactionRequest)
  private static final com.betpawa.wallet.TransactionRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.betpawa.wallet.TransactionRequest();
  }

  public static com.betpawa.wallet.TransactionRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TransactionRequest>
      PARSER = new com.google.protobuf.AbstractParser<TransactionRequest>() {
    @java.lang.Override
    public TransactionRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TransactionRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TransactionRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TransactionRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.betpawa.wallet.TransactionRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}


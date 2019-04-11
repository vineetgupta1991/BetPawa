// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: WalletService.proto

package com.betpawa.wallet;

public interface BalanceResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.betpawa.wallet.BalanceResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>map&lt;string, double&gt; balanceByCurrency = 1;</code>
   */
  int getBalanceByCurrencyCount();
  /**
   * <code>map&lt;string, double&gt; balanceByCurrency = 1;</code>
   */
  boolean containsBalanceByCurrency(
      java.lang.String key);
  /**
   * Use {@link #getBalanceByCurrencyMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.Double>
  getBalanceByCurrency();
  /**
   * <code>map&lt;string, double&gt; balanceByCurrency = 1;</code>
   */
  java.util.Map<java.lang.String, java.lang.Double>
  getBalanceByCurrencyMap();
  /**
   * <code>map&lt;string, double&gt; balanceByCurrency = 1;</code>
   */

  double getBalanceByCurrencyOrDefault(
      java.lang.String key,
      double defaultValue);
  /**
   * <code>map&lt;string, double&gt; balanceByCurrency = 1;</code>
   */

  double getBalanceByCurrencyOrThrow(
      java.lang.String key);
}

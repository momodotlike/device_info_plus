// Copyright 2017 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package dev.fluttercommunity.plus.device_info;

import android.content.Context;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;

/** DeviceInfoPlusPlugin */
public class DeviceInfoPlusPlugin implements FlutterPlugin {

  MethodChannel channel;

  @Override
  public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding binding) {
    setupMethodChannel(binding.getBinaryMessenger(), binding.getApplicationContext());
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPlugin.FlutterPluginBinding binding) {
    tearDownChannel();
  }

  private void setupMethodChannel(BinaryMessenger messenger, Context context) {
    channel = new MethodChannel(messenger, "dev.fluttercommunity.plus/device_info");
    final MethodCallHandlerImpl handler =
        new MethodCallHandlerImpl(context.getContentResolver(), context.getPackageManager());
    channel.setMethodCallHandler(handler);
  }

  private void tearDownChannel() {
    channel.setMethodCallHandler(null);
    channel = null;
  }
}

/*
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.facebook.imagepipeline.producers;

import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * Represents a local file fetch producer.
 */
public class LocalFileFetchProducer extends LocalFetchProducer {

  public static final String PRODUCER_NAME = "LocalFileFetchProducer";

  public LocalFileFetchProducer(
      Executor executor,
      PooledByteBufferFactory pooledByteBufferFactory) {
    super(executor, pooledByteBufferFactory);
  }

  @Override
  protected EncodedImage getEncodedImage(final ImageRequest imageRequest) throws IOException {
    return getEncodedImage(
        new FileInputStream(imageRequest.getSourceFile().toString()),
        (int) imageRequest.getSourceFile().length());
  }

  @Override
  protected String getProducerName() {
    return PRODUCER_NAME;
  }
}

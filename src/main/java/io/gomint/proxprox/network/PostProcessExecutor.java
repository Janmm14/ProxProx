/*
 * Copyright (c) 2017, GoMint, BlackyPaw and geNAZt
 *
 * This code is licensed under the BSD license found in the
 * LICENSE file in the root directory of this source tree.
 */

package io.gomint.proxprox.network;

import io.gomint.jraknet.PacketBuffer;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author geNAZt
 * @version 1.0
 */
@EqualsAndHashCode
public class PostProcessExecutor {

    @Getter
    private AtomicInteger connectionsInUse = new AtomicInteger( 0 );
    private Executor executor = Executors.newSingleThreadExecutor();

    public void addWork( AbstractConnection connection, PacketBuffer[] packets ) {
        this.executor.execute( new PostProcessWorker( connection, packets ) );
    }

}

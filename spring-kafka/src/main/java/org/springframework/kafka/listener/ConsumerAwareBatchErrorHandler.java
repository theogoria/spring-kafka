/*
 * Copyright 2017-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.kafka.listener;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import org.springframework.lang.Nullable;

/**
 * An error handler that has access to the consumer, for example to adjust
 * offsets after an error.
 *
 * @author Gary Russell
 * @since 2.0
 *
 */
@FunctionalInterface
public interface ConsumerAwareBatchErrorHandler extends BatchErrorHandler {

	@Override
	default void handle(Exception thrownException, @Nullable ConsumerRecords<?, ?> data) {
		throw new UnsupportedOperationException("Container should never call this");
	}

	@Override
	void handle(Exception thrownException, ConsumerRecords<?, ?> data, Consumer<?, ?> consumer);

	@Override
	default void handle(Exception thrownException, @Nullable ConsumerRecords<?, ?> data, Consumer<?, ?> consumer,
			MessageListenerContainer container) {

		handle(thrownException, data, consumer);
	}

}

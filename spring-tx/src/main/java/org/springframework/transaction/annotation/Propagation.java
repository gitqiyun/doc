/*
 * Copyright 2002-2019 the original author or authors.
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

package org.springframework.transaction.annotation;

import org.springframework.transaction.TransactionDefinition;

/**
 * spring事务传播性枚举
 * 事务传播行为：多个事务方法相互调用时，事务在这些方法间传播行为，
 * 事务传播行为是Spring框架独有的事务增强特性，他不属于的事务实际提供方数据库行为
 * serviceA{
 * methodA(){
 * serviceB.methodB
 * }
 * }
 * serviceB{
 * methodB{
 * <p>
 * }
 * }
 * methodA里调用serviceB.methodB会产生新事务还是同一个事务，取决于设置的Propagation值
 */
public enum Propagation {

	/**
	 * 支持当前事务，如果不存在则创建新事务。
	 * 这是事务注释的默认设置。
	 */
	REQUIRED(TransactionDefinition.PROPAGATION_REQUIRED),

	/**
	 * 支持当前事务，如果不存在则以非事务方式执行.
	 *
	 * @see org.springframework.transaction.support.AbstractPlatformTransactionManager#setTransactionSynchronization
	 */
	SUPPORTS(TransactionDefinition.PROPAGATION_SUPPORTS),

	/**
	 * 支持当前事务，如果不存在则抛出异常。
	 */
	MANDATORY(TransactionDefinition.PROPAGATION_MANDATORY),

	/**
	 * 创建一个新事务，并暂停当前事务（如果存在）
	 */
	REQUIRES_NEW(TransactionDefinition.PROPAGATION_REQUIRES_NEW),

	/**
	 * 以非事务方式执行，暂停当前事务（如果存在）。
	 */
	NOT_SUPPORTED(TransactionDefinition.PROPAGATION_NOT_SUPPORTED),

	/**
	 * 以非事务方式执行, 如果事务存在则抛出异常。
	 */
	NEVER(TransactionDefinition.PROPAGATION_NEVER),

	/**
	 * 如果存在当前事务，则在嵌套事务中执行，
	 * 表现得像{@code REQUIRED}否则。 EJB中没有类似的功能。
	 * <p>注意：实际创建嵌套事务仅适用于特定事务
	 * 交易经理。开箱即用，这仅适用于JDBC
	 * DataSourceTransactionManager。一些JTA提供程序可能支持嵌套
	 * 交易也是如此。
	 */
	NESTED(TransactionDefinition.PROPAGATION_NESTED);


	private final int value;


	Propagation(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}

}

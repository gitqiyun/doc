/*
 * Copyright 2002-2018 the original author or authors.
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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.TransactionDefinition;

/**
 * Describes a transaction attribute on an individual method or on a class.
 *
 * <p>At the class level, this annotation applies as a default to all methods of
 * the declaring class and its subclasses. Note that it does not apply to ancestor
 * classes up the class hierarchy; methods need to be locally redeclared in order
 * to participate in a subclass-level annotation.
 *
 * <p>This annotation type is generally directly comparable to Spring's
 * {@link org.springframework.transaction.interceptor.RuleBasedTransactionAttribute}
 * class, and in fact {@link AnnotationTransactionAttributeSource} will directly
 * convert the data to the latter class, so that Spring's transaction support code
 * does not have to know about annotations. If no rules are relevant to the exception,
 * it will be treated like
 * {@link org.springframework.transaction.interceptor.DefaultTransactionAttribute}
 * (rolling back on {@link RuntimeException} and {@link Error} but not on checked
 * exceptions).
 *
 * <p>For specific information about the semantics of this annotation's attributes,
 * consult the {@link org.springframework.transaction.TransactionDefinition} and
 * {@link org.springframework.transaction.interceptor.TransactionAttribute} javadocs.
 *
 * @author Colin Sampaleanu
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @see org.springframework.transaction.interceptor.TransactionAttribute
 * @see org.springframework.transaction.interceptor.DefaultTransactionAttribute
 * @see org.springframework.transaction.interceptor.RuleBasedTransactionAttribute
 * @since 1.2
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Transactional {

	/**
	 * Alias for {@link #transactionManager}.
	 *
	 * @see #transactionManager
	 */
	@AliasFor("transactionManager")
	String value() default "";

	/**
	 * A <em>qualifier</em> value for the specified transaction.
	 * <p>May be used to determine the target transaction manager,
	 * matching the qualifier value (or the bean name) of a specific
	 * {@link org.springframework.transaction.PlatformTransactionManager}
	 * bean definition.
	 *
	 * @see #value
	 * @since 4.2
	 */
	@AliasFor("value")
	String transactionManager() default "";

	/**
	 * 事务传播类型.
	 */
	Propagation propagation() default Propagation.REQUIRED;

	/**
	 * 事务隔离级别.
	 * <p>默认为{@link Isolation＃DEFAULT}。
	 * <p>专为{@link Propagation＃REQUIRED}或使用而设计
	 * {@link Propagation #REQUIRES_NEW}，因为它仅适用于新启动的
	 * 交易。考虑将“validateExistingTransactions”标志切换为
	 * 如果您想要隔离级别声明，请在事务管理器上使用“true”
	 * 在参与与其他交易的现有交易时被拒绝
	 * 隔离级别。
	 */
	Isolation isolation() default Isolation.DEFAULT;

	/**
	 * 此事务的超时（以秒为单位）。
	 * <p>默认为基础交易系统的默认超时。
	 * <p>专为{@link Propagation＃REQUIRED}或使用而设计
	 * {@link Propagation #REQUIRES_NEW}，因为它仅适用于新启动的
	 * 交易。
	 */
	int timeout() default TransactionDefinition.TIMEOUT_DEFAULT;

	/**
	 * 读写或只读事务
	 */
	boolean readOnly() default false;

	/**
	 * 定义零（0）或更多异常{@link Class classes}（Class对象数组），必须是
	 * {@link Throwable}的子类，指示哪些异常类型必须导致事务回滚。
	 * 默认情况下，事务将在{@link RuntimeException}上回滚
	 */
	Class<? extends Throwable>[] rollbackFor() default {};

	/**
	 * 类名数组，必须继承自Throwable
	 */
	String[] rollbackForClassName() default {};

	/**
	 * 定义哪些异常class不回滚
	 */
	Class<? extends Throwable>[] noRollbackFor() default {};

	/**
	 * 定义哪些异常类名不回滚
	 */
	String[] noRollbackForClassName() default {};

}

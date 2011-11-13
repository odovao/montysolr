package org.apache.lucene.queryParser.aqp.config;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.lucene.queryParser.core.config.QueryConfigHandler;
import org.apache.lucene.queryParser.standard.processors.PhraseSlopQueryNodeProcessor;
import org.apache.lucene.util.AttributeImpl;

/**
 * This attribute is used by {@link PhraseSlopQueryNodeProcessor} processor and
 * must be defined in the {@link QueryConfigHandler}. This attribute tells the
 * processor what is the default phrase slop when no slop is defined in a
 * phrase. <br/>
 * 
 * @see org.apache.lucene.queryParser.standard.config.DefaultOperatorAttribute
 */
public class DefaultProximityAttributeImpl extends AttributeImpl implements
		DefaultProximityAttribute {

	private static final long serialVersionUID = 1591861254033678970L;

	private int defaultProximity = 0;

	public DefaultProximityAttributeImpl() {
		defaultProximity = 5; // default value in 2.4
	}

	public void setDefaultProximity(int defaultProximity) {
		this.defaultProximity = defaultProximity;
	}

	public int getDefaultProximity() {
		return this.defaultProximity;
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void copyTo(AttributeImpl target) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean equals(Object other) {

		if (other instanceof DefaultProximityAttributeImpl
				&& ((DefaultProximityAttributeImpl) other).defaultProximity == this.defaultProximity) {

			return true;

		}

		return false;

	}

	@Override
	public int hashCode() {
		return Integer.valueOf(this.defaultProximity).hashCode();
	}

	@Override
	public String toString() {
		return "<DefaultProximity defaultProximity=" + this.defaultProximity
				+ "/>";
	}

}
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.commons.core.edm.primitivetype;

import java.sql.Timestamp;
import java.util.regex.Pattern;

import org.apache.olingo.commons.api.edm.EdmPrimitiveType;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeException;

public class EdmTimespan extends SingletonPrimitiveType {

  private static final Pattern PATTERN_ASCII = Pattern.compile("\\p{ASCII}*");

  private static final EdmTimespan INSTANCE = new EdmTimespan();

  public static EdmTimespan getInstance() {
    return INSTANCE;
  }

  @Override
  public Class<?> getDefaultType() {
    return String.class;
  }

  @Override
  public boolean isCompatible(final EdmPrimitiveType primitiveType) {
    return primitiveType instanceof EdmDateTimeOffset;
  }
  
  @Override
  protected <T> T internalValueOfString(final String value,
      final Boolean isNullable, final Integer maxLength, final Integer precision,
      final Integer scale, final Boolean isUnicode, final Class<T> returnType) throws EdmPrimitiveTypeException {
      
    String[] split = value.split("\\/");
    EdmDateTimeOffset timeParser = EdmDateTimeOffset.getInstance();
    
    // Validate individual Timestamps
    timeParser.internalValueOfString(split[0], isNullable, maxLength, precision, scale, isUnicode, Timestamp.class);
    timeParser.internalValueOfString(split[1], isNullable, maxLength, precision, scale, isUnicode, Timestamp.class);
    
    if (isUnicode != null && !isUnicode && !PATTERN_ASCII.matcher(value).matches()
            || maxLength != null && maxLength < value.length()) {
          throw new EdmPrimitiveTypeException("The literal '" + value + "' does not match the facets' constraints.");
        }
    
    if (returnType.isAssignableFrom(String.class)) {
        return returnType.cast(value);
      } else {
        throw new EdmPrimitiveTypeException("The value type " + returnType + " is not supported.");
      }
  }

  @Override
  protected <T> String internalValueToString(final T value,
      final Boolean isNullable, final Integer maxLength, final Integer precision,
      final Integer scale, final Boolean isUnicode) throws EdmPrimitiveTypeException {

    final String result = value instanceof String ? (String) value : String.valueOf(value);

    if (isUnicode != null && !isUnicode && !PATTERN_ASCII.matcher(result).matches()
        || maxLength != null && maxLength < result.length()) {
      throw new EdmPrimitiveTypeException("The value '" + value + "' does not match the facets' constraints.");
    }

    return result;
  }
}
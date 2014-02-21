/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.odata4.client.core.edm.v4;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.ArrayList;
import java.util.List;
import org.apache.olingo.odata4.client.api.edm.v4.Annotations;
import org.apache.olingo.odata4.client.core.edm.AbstractAnnotations;

@JsonDeserialize(using = AnnotationsDeserializer.class)
public class AnnotationsImpl extends AbstractAnnotations implements Annotations {

  private static final long serialVersionUID = 3877353656301805410L;

  private final List<AnnotationImpl> annotations = new ArrayList<AnnotationImpl>();

  public List<AnnotationImpl> getAnnotations() {
    return annotations;
  }

  public AnnotationImpl getAnnotation(final String term) {
    AnnotationImpl result = null;
    for (AnnotationImpl annotation : getAnnotations()) {
      if (term.equals(annotation.getTerm())) {
        result = annotation;
      }
    }
    return result;
  }
}

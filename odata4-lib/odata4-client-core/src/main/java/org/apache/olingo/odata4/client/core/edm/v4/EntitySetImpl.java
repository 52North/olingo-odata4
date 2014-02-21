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

import java.util.ArrayList;
import java.util.List;
import org.apache.olingo.odata4.client.api.edm.NavigationPropertyBinding;
import org.apache.olingo.odata4.client.api.edm.v4.Annotation;
import org.apache.olingo.odata4.client.core.edm.AbstractEntitySet;

public class EntitySetImpl extends AbstractEntitySet implements AnnotatedEdmItem {

  private static final long serialVersionUID = 5570833733884884012L;

  private boolean includeInServiceDocument = true;

  private Annotation annotation;

  private final List<NavigationPropertyBinding> navigationPropertyBindings
          = new ArrayList<NavigationPropertyBinding>();

  public boolean isIncludeInServiceDocument() {
    return includeInServiceDocument;
  }

  public void setIncludeInServiceDocument(final boolean includeInServiceDocument) {
    this.includeInServiceDocument = includeInServiceDocument;
  }

  public List<NavigationPropertyBinding> getNavigationPropertyBindings() {
    return navigationPropertyBindings;
  }

  @Override
  public Annotation getAnnotation() {
    return annotation;
  }

  @Override
  public void setAnnotation(final Annotation annotation) {
    this.annotation = annotation;
  }

}

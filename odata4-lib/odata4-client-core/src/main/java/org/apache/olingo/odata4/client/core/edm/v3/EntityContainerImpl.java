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
package org.apache.olingo.odata4.client.core.edm.v3;

import java.util.ArrayList;
import java.util.List;
import org.apache.olingo.odata4.client.core.edm.AbstractEntityContainer;

public class EntityContainerImpl extends AbstractEntityContainer<FunctionImportImpl> {

  private static final long serialVersionUID = 8934431875078180370L;

  private final List<EntitySetImpl> entitySets = new ArrayList<EntitySetImpl>();

  private final List<AssociationSetImpl> associationSets = new ArrayList<AssociationSetImpl>();

  private final List<FunctionImportImpl> functionImports = new ArrayList<FunctionImportImpl>();

  @Override
  public List<EntitySetImpl> getEntitySets() {
    return entitySets;
  }

  @Override
  public EntitySetImpl getEntitySet(final String name) {
    EntitySetImpl result = null;
    for (EntitySetImpl entitySet : getEntitySets()) {
      if (name.equals(entitySet.getName())) {
        result = entitySet;
      }
    }
    return result;
  }

  public List<AssociationSetImpl> getAssociationSets() {
    return associationSets;
  }

  @Override
  public List<FunctionImportImpl> getFunctionImports() {
    return functionImports;
  }

}

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
package org.apache.olingo.server.core.uri.queryoption;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.olingo.commons.api.edm.EdmType;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;
import org.apache.olingo.server.api.uri.UriInfoResource;
import org.apache.olingo.server.api.uri.queryoption.ApplyOption;
import org.apache.olingo.server.api.uri.queryoption.CountOption;
import org.apache.olingo.server.api.uri.queryoption.ExpandItem;
import org.apache.olingo.server.api.uri.queryoption.ExpandOption;
import org.apache.olingo.server.api.uri.queryoption.FilterOption;
import org.apache.olingo.server.api.uri.queryoption.LevelsExpandOption;
import org.apache.olingo.server.api.uri.queryoption.OrderByOption;
import org.apache.olingo.server.api.uri.queryoption.SearchOption;
import org.apache.olingo.server.api.uri.queryoption.SelectOption;
import org.apache.olingo.server.api.uri.queryoption.SkipOption;
import org.apache.olingo.server.api.uri.queryoption.SystemQueryOption;
import org.apache.olingo.server.api.uri.queryoption.TopOption;

public class ExpandItemImpl implements ExpandItem {
  private LevelsExpandOption levelsExpandOption;
  private FilterOption filterOption;
  private SearchOption searchOption;
  private OrderByOption orderByOption;
  private SkipOption skipOption;
  private TopOption topOption;
  private CountOption inlineCountOption;
  private SelectOption selectOption;
  private ExpandOption expandOption;
  private ApplyOption applyOption;

  private UriInfoResource resourceInfo;

  private boolean isStar;

  private boolean isRef;
  private boolean hasCountPath;
  private EdmType startTypeFilter;

  public ExpandItemImpl setSystemQueryOption(final SystemQueryOption sysItem) {

    if (sysItem instanceof ApplyOption) {
      validateDoubleSystemQueryOption(applyOption, sysItem);
      applyOption = (ApplyOption) sysItem;
    } else if (sysItem instanceof ExpandOption) {
      if (expandOption != null) {
        mergeExpandItems((ExpandOption) sysItem);
      } else {
        expandOption = (ExpandOption) sysItem;
      }
    } else if (sysItem instanceof FilterOption) {
      validateDoubleSystemQueryOption(filterOption, sysItem);
      filterOption = (FilterOption) sysItem;
    } else if (sysItem instanceof CountOption) {
      validateDoubleSystemQueryOption(inlineCountOption, sysItem);
      inlineCountOption = (CountOption) sysItem;
    } else if (sysItem instanceof OrderByOption) {
      validateDoubleSystemQueryOption(orderByOption, sysItem);
      orderByOption = (OrderByOption) sysItem;
    } else if (sysItem instanceof SearchOption) {
      validateDoubleSystemQueryOption(searchOption, sysItem);
      searchOption = (SearchOption) sysItem;
    } else if (sysItem instanceof SelectOption) {
      validateDoubleSystemQueryOption(selectOption, sysItem);
      selectOption = (SelectOption) sysItem;
    } else if (sysItem instanceof SkipOption) {
      validateDoubleSystemQueryOption(skipOption, sysItem);
      skipOption = (SkipOption) sysItem;
    } else if (sysItem instanceof TopOption) {
      validateDoubleSystemQueryOption(topOption, sysItem);
      topOption = (TopOption) sysItem;
    } else if (sysItem instanceof LevelsExpandOption) {
      if (levelsExpandOption != null) {
        throw new ODataRuntimeException("$levels");
      }
      levelsExpandOption = (LevelsExpandOption) sysItem;
    }
    return this;
  }
  
  protected void mergeExpandItems(ExpandOption newOption) {
    ExpandOptionImpl finalExpandImpl = (ExpandOptionImpl) expandOption;
    List<ExpandItem> oldExpandItems = finalExpandImpl.getExpandItems();
    List<ExpandItem> newExpandItems = newOption.getExpandItems();
    
    Iterator<ExpandItem> iter = newExpandItems.iterator();
    while (iter.hasNext()) {
      ExpandItem element = iter.next();
      // Recursively join
      int index = oldExpandItems.indexOf(element);
      // Modify existing Item if present, else add new one
      if (index != -1) {
        ((ExpandItemImpl)oldExpandItems.get(index)).mergeExpandItems(element.getExpandOption());
      } else {
        finalExpandImpl.addExpandItem(element);
      }
    }
    expandOption = finalExpandImpl;
  }

  private void validateDoubleSystemQueryOption(final SystemQueryOption oldOption, final SystemQueryOption newOption) {
    if (oldOption != null) {
      throw new ODataRuntimeException(newOption.getName());
    }
  }

  public ExpandItemImpl setSystemQueryOptions(final List<SystemQueryOption> list) {
    for (SystemQueryOption item : list) {
      setSystemQueryOption(item);
    }
    return this;
  }

  @Override
  public LevelsExpandOption getLevelsOption() {
    return levelsExpandOption;
  }

  @Override
  public FilterOption getFilterOption() {
    return filterOption;
  }

  @Override
  public SearchOption getSearchOption() {
    return searchOption;
  }

  @Override
  public OrderByOption getOrderByOption() {
    return orderByOption;
  }

  @Override
  public SkipOption getSkipOption() {
    return skipOption;
  }

  @Override
  public TopOption getTopOption() {
    return topOption;
  }

  @Override
  public CountOption getCountOption() {
    return inlineCountOption;
  }

  @Override
  public SelectOption getSelectOption() {

    return selectOption;
  }

  @Override
  public ExpandOption getExpandOption() {
    return expandOption;
  }

  @Override
  public ApplyOption getApplyOption() {
    return applyOption;
  }

  public ExpandItemImpl setResourcePath(final UriInfoResource resourceInfo) {
    this.resourceInfo = resourceInfo;
    return this;
  }

  @Override
  public UriInfoResource getResourcePath() {

    return resourceInfo;
  }

  @Override
  public boolean isStar() {
    return isStar;
  }

  public ExpandItemImpl setIsStar(final boolean isStar) {
    this.isStar = isStar;
    return this;
  }

  @Override
  public boolean isRef() {
    return isRef;
  }

  public ExpandItemImpl setIsRef(final boolean isRef) {
    this.isRef = isRef;
    return this;
  }

  @Override
  public EdmType getStartTypeFilter() {
    return startTypeFilter;
  }

  public ExpandItemImpl setTypeFilter(final EdmType startTypeFilter) {
    this.startTypeFilter = startTypeFilter;
    return this;
  }

  @Override
  public boolean hasCountPath() {
    return this.hasCountPath;
  }
  
  public void setCountPath(boolean value) {
    this.hasCountPath = value;
  }
  
  
  @Override
  public boolean equals(Object o) {
      if (!(o instanceof ExpandItem)) {
          return false;
      }
      ExpandItem item = (ExpandItem) o;
      return equalsWithNull(item.getResourcePath().getUriResourceParts(), this.getResourcePath().getUriResourceParts())
          && equalsWithNull(item.getApplyOption(), this.getApplyOption())
          && equalsWithNull(item.getCountOption(), this.getCountOption())
          && equalsWithNull(item.getFilterOption(), this.getFilterOption())
          && equalsWithNull(item.getLevelsOption(), this.getLevelsOption())
          && equalsWithNull(item.getOrderByOption(), this.getOrderByOption())
          && equalsWithNull(item.getSelectOption(), this.getSelectOption())
          && equalsWithNull(item.getSkipOption(), this.getSkipOption())
          && equalsWithNull(item.getTopOption(), this.getTopOption());
  }
  
  private boolean equalsWithNull(Object o1, Object o2) {
    if (o1 == null && o2 == null) {
      return true;
    } else if (o1 == null || o2 == null) {
      return false;
    } else {
      return o1.toString().equals(o2.toString());
    }
  }

  @Override
  public int hashCode() {
      return Objects.hash(levelsExpandOption,
                          filterOption,
                          searchOption,
                          orderByOption,
                          skipOption,
                          topOption,
                          inlineCountOption,
                          selectOption,
                          applyOption);
  }
}

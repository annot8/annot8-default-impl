package io.annot8.defaultimpl.factories;

import io.annot8.common.implementations.factories.ItemCreator;
import io.annot8.common.implementations.registries.ContentBuilderFactoryRegistry;
import io.annot8.core.data.Item;
import io.annot8.core.data.ItemFactory;
import io.annot8.defaultimpl.data.DefaultItem;
import java.util.Objects;

public class DefaultItemCreator implements ItemCreator {

  private final ContentBuilderFactoryRegistry contentBuilderFactoryRegistry;

  public DefaultItemCreator(ContentBuilderFactoryRegistry contentBuilderFactoryRegistry) {
    this.contentBuilderFactoryRegistry = contentBuilderFactoryRegistry;
  }

  @Override
  public Item create(ItemFactory factory) {
    return new DefaultItem(factory, contentBuilderFactoryRegistry);
  }

  @Override
  public Item create(ItemFactory factory, Item parent) {
    Objects.requireNonNull(parent);
    return new DefaultItem(factory, parent.getId(), contentBuilderFactoryRegistry);
  }

}
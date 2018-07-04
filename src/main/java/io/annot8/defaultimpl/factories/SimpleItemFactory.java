package io.annot8.defaultimpl.factories;

import io.annot8.core.data.ItemFactory;
import io.annot8.common.registries.ContentBuilderFactoryRegistry;
import io.annot8.core.data.Item;
import io.annot8.defaultimpl.data.SimpleItem;
import io.annot8.defaultimpl.pipeline.SimpleItemQueue;
import java.util.function.Consumer;

public class SimpleItemFactory implements ItemFactory {

  private final ContentBuilderFactoryRegistry contentBuilderFactoryRegistry;
  private final Consumer<Item> onCreateConsumer;

  public SimpleItemFactory(ContentBuilderFactoryRegistry contentBuilderFactoryRegistry, Consumer<Item> onCreateConsumer){
    this.contentBuilderFactoryRegistry = contentBuilderFactoryRegistry;
    this.onCreateConsumer = onCreateConsumer;
  }

  @Override
  public Item create() {
    return consume(new SimpleItem(this, contentBuilderFactoryRegistry));
  }

  @Override
  public Item create(Item parent) {
    return create();
  }

  protected Item consume(Item item) {
    if(onCreateConsumer != null) {
      onCreateConsumer.accept(item);
    }
    return item;
  }
}
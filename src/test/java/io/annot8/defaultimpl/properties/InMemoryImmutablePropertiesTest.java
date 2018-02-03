package io.annot8.defaultimpl.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import org.junit.jupiter.api.Test;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.properties.ImmutableProperties;

public class InMemoryImmutablePropertiesTest {
  @Test
  public void testImmutableProperties() throws IncompleteException{
    ImmutableProperties props1 = new InMemoryImmutableProperties.Builder()
        .withProperty("key1", "Hello World")
        .withProperty("key2", Integer.valueOf(17))
        .build();
    testMap(props1.getAll());

    ImmutableProperties props2 = new InMemoryImmutableProperties.Builder()
        .withProperty("key3", false)    //This will be removed as from(...) creates an exact copy of the parameter
        .from(props1)
        .build();
    testMap(props2.getAll());
    
    ImmutableProperties props3 = new InMemoryImmutableProperties.Builder()
        .withProperty("key3", false)    //This won't be removed, as withProperties(...) adds to existing properties
        .withProperty("key1", "To be overwritten")
        .withProperties(props1)
        .build();
    
    Map<String, Object> map3 = props3.getAll();
    assertEquals(3, map3.size());
    assertEquals("Hello World", map3.get("key1"));
    assertEquals(Integer.valueOf(17), map3.get("key2"));
    assertEquals(false, map3.get("key3"));
  }

  private void testMap(Map<String, Object> map) {
    assertEquals(2, map.size());
    assertEquals("Hello World", map.get("key1"));
    assertEquals(Integer.valueOf(17), map.get("key2"));
  }
}

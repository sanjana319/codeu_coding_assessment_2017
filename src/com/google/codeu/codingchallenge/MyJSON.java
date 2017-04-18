// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.codingchallenge;

import java.util.Collection;

final class MyJSON implements JSON {
  // GET OBJECT
  //
  // Get the value of the nested object with the given name. If there is
  // no nested object with that name, the method will return null.
  private HashMap<String, Object> map = new HashMap<String, Object>(); //make a hashmap to store JSON objects

  @Override
  public JSON getObject(String name) {
    // TODO: implement this
    if(map.containsKey(name) && map.get(name) instanceof JSON){
      return (JSON) map.get(name);
    }

    return null;
  }

  // SET OBJECT
  //
  // Set the value of the nested object with the given name. Any old value
  // should be overwritten. This method will always return a reference to
  // "this".
  @Override
  public JSON setObject(String name, JSON value) {
    // TODO: implement this
    if(map.containsKey(name) && map.get(name) instanceof JSON){
      map.remove(name);
    }
    map.put(name, value);
    return this;
  }

  // GET STRING
  //
  // Get the string value within this object that has the given name. if
  // there is no string with the given name, the method will return null.
  @Override
  public String getString(String name) {
    // TODO: implement this
    if(map.containsKey(name) && map.get(name) instanceof String){
      return (String) map.get(name);
    }
    return null;
  }

  // SET STRING
  //
  // Set the string that should be stored under the given name. Any old value
  // should be overwritten. This method will always return a reference to
  // "this".
  @Override
  public JSON setString(String name, String value) {
    // TODO: implement this
    if(map.containsKey(name) && map.get(name) instanceof String){
      map.remove(name);
    } //overwrite old value
    map.put(name, value); //add new one
    return this;
  }
  // GET OBJECTS
  //
  // Copy the names of all object values to the given collection.
  @Override
  public void getObjects(Collection<String> names) {
    // TODO: implement this
    for(String key: map.keySet()){
      if(map.get(key) instanceof Object){
        names.add(key);
      }
    }
  }

  // GET STRINGS
  //
  // Copy the names of all string values to the given collection.
  @Override
  public void getStrings(Collection<String> names) {
    // TODO: implement this
    for(String key: map.keySet()){
      if(map.get(key) instanceof String){
        names.add(key);
      }
    }
  }
}

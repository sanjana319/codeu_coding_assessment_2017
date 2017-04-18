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

import java.io.IOException;

final class MyJSONParser implements JSONParser {

  @Override
  public JSON parse(String in) throws IOException {
    // TODO: implement this
		JSON j = new MyJSON(); //the object to be returned 
		String s = in; //temp string to mess with and check for input
		String tempchar, tempkey, tempvalue; //temp stuff for s 

		int index = 0;
		int end, next; 
		//use these guys to parse through the input in

		/*String temp = in.trim();
		String first = temp.charAt(0);
		String last = temp.charAt(temp.length() - 1);

		if(!first.equals("{") || !last.equals("}")){
			throw new IOException; 
		}*/

		for(int i = 0; i < in.length(); i++){ 
			tempchar = in.substring(i, i+1); //checking if the first character of in is valid
			if(tempchar.equals("{")){
				end = in.indexOf("}")--; //if it has a {, then find the index of } and assign it
				s = in.substring(i++);
				
				while(index < s.length()){
					tempchar = s.substring(index, index++);
					if(tempchar.equals(" ")){
						index++;
					} //parse through the input 

					 //break if you reach } bc you've reached the end

					if(tempchar.equals("}")){break;}

					else if(tempchar.equals("\"")){
						s = s.substring(index++);
						index = s.indexOf("\"");
						tempkey = s.substring(0, index);
						s = s.substring(index++);
						index = 0;
						next = s.indexOf("\"");

						if(s.contains(",") == true){
							index = s.indexOf(",");
							s = s.substring(index++);
							index = 0;
						}

						else{
							in = "";
							break;
						}

						String c = s.substring(index, next);

						if(c.contains("{")){
							int endval = s.indexOf("}");
							String val = s.substring(0, endval+++);
							JSON v = parse(val);
							j.setObject(key, v);
							s = s.substring(endval++);
						}

						else{
							s=s.substring(next++);
							index = s.indexOf("\"");
							tempvalue = s.substring(0, index);
							j.setString(key, value);
						}



						if(!s.contains(":")){
							throw new IOException;
						}
					}

					}
				}
			}
		}
		return j;
	}
  }

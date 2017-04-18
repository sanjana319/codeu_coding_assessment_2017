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
    JSON j = new MyJSON();
    int last = in.length() - 1;
    if(validString(in) == true || validObj(in) == true){
    	int first = in.indexOf('{');
    	String s = in.substring(first++,last).trim();
    	int slength = s.length();
    	if(slength == 0){
    		return j;
    	}
    	else{
    		if(s.contains(':') == false){
    			throw new IOException();
    		}

    		int c = s.indexOf(':'); //find index of the colon in input
    		String input = s.substring(0, c).trim(); //trim input for first half of string
    		int ilength = input.length() - 1;

    		if(input.charAt(0) != '\"' || input.chatAt(ilength) != '\"'){
    			throw new IOException();
    		}
    		else{
    			input = input.substring(1, ilength);
    			String val = s.substring(c++).trim();
    		}

    		if(validObj(val)){
    			j.setObject(input, parse(val));
    		}

    		else{
    			String[] objects = s.split(",");
    			for(String str: objects){
    				str = str.trim();
    				strColon = str.indexOf(':');
    				if(str.contains(':') == false){
    					throw new IOException();
    				}

				String strName = str.substring(0, strColon);
				int strNameL = strName.length() - 1;
				if(!validString(strName)){
					throw new IOException();
				}

				strName = strName.substring(1, strNameL);
				String strVal = str.substring(strColon+ 1).trim();
				int strValL = strVal.length() - 1;


				if(!validString(strVal)){
					throw new IOException();
				}

				strVal = strVal.substring(1, strValL).trim();
				j.setString(strName, strVal);
    			}

    			if(!escapeChars(in)){
    				throw new IOException();
    			}
    		}
    	}
    }

    else{
    	throw new IOException();
    }

    return j;
	} //end parse

	//check if string is valid for JSON-lite
	public boolean validString(String str){
		str = str.trim(); //trim whitespace
		int strLength = str.length();
		String firstCharacter = str.charAt(0); //check if it's valid by looking at first and last characters.
		String lastCharacter = str.charAt(str.length() - 1);

		if(strLength < 2){
			return false;
		}
		
		if(firstCharacter.equals('\"') == false && lastCharacter.equals('\"') == false{
			return false;
		}
		
		return true;
	}

	//check if string is a valid JSON object
	public boolean validObj(String str){
		str = str.trim();
		int length =  str.length();
		int colon = str.indexOf(':');
		
		String firstCharacter = str.charAt(0);
		String lastCharacter = str.charAt(str.length() - 1);

		if(length < 2){
			return false;
		}
		
		if(firstCharacter.equals('{') == false && lastCharacter.equals('}') == false){
			return false;
		}
		return true;
	}

	public boolean escapeChars(String str){
		for(int i =0; i < str.length() - 1; i++){
			char c = str.charAt(i);
			if(c == '\\'){
				char next = str.charAt(i++);
				if(next != 'n'){
					if(next != 't'){
						return false;
					}
				}
			}
		}
		return true;
	}
  }

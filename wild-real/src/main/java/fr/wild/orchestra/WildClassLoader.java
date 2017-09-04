/*
 *  ONEMA Dice project.
 *  Copyright (C) 2016 ONEMA
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project. If not, see http://www.gnu.org/licenses.
 */
package fr.wild.orchestra;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

/**
 *
 * @author alexandre.liccardi
 * @version [ab]
 */
public class WildClassLoader extends URLClassLoader{
    private HashMap <String, String> jarPathForModels = new HashMap <String, String> () ;

    public WildClassLoader(URLClassLoader classLoader) {
        super(classLoader.getURLs());
    }

    public WildClassLoader(HashMap <String, String> i_jarPathForModels) {
        super(((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs());
        jarPathForModels = i_jarPathForModels;
        for(String k:jarPathForModels.keySet())add(jarPathForModels.get(k));
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

    public void add(String i_jarPath){
        if(i_jarPath==null)return;
        try {
            super.addURL((new File(i_jarPath)).toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String getModelJarPath(String modelName) {
        return jarPathForModels.get(modelName);
    }

}
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

import static fr.wild.common.IoCommons.*;


/**
 * Un token comprend les informations d'identification d'un DiceRunnable,
 * Identifiants uniques générées aléatoirement et noms d'éléments parcourus (généalogie WILD)
 * @author alexandre.liccardi
 * @version [ab]
 */
public class WildToken {

    private String[] arrayTokens ; // Liste des identifiants uniques
    private String[] arrayWilds ; // Liste des noms d'éléments WILD

    /**
     * Constructeur, rattaché à la création d'un DiceRunnable
     * @param i_arrayTokens    Identifiant uniques précédents, un nouvel identifiant sera ajouté
     * @param i_arrayWilds    Noms d'éléments précédents (généalogie WILD)
     */
    public WildToken(String[] i_arrayTokens, String[] i_arrayWilds) {
        arrayTokens = new String[i_arrayTokens.length+1];
        int i = 0;
        for(String arTok : i_arrayTokens)arrayTokens[i++]=arTok; // Ajout d'une clé spécifique au nouvel élément
        arrayTokens[i] = cast_getRandom();
        arrayWilds = i_arrayWilds;
    }

    /**
     * Récupération des noms d'élément WILD
     * @return
     */
    public String[] getArrayWild() {
        return arrayWilds;
    }

    /**
     * Récupération des identifants uniques
     * @return
     */
    public String[] getArrayTokens() {
        return arrayTokens;
    }

    /**
     * Récupération du dernier identifant unique, correspondant à l'identifiant de l'élément portant le WildToken
     * @return
     */
    public String getLastToken(){
        return arrayTokens[arrayTokens.length-1];
    }
}
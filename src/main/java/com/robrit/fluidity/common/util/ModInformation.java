/*
 * ModInformation.java
 *
 * Copyright (c) 2015 TheRoBrit
 *
 * Fluidity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Fluidity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.robrit.fluidity.common.util;

public class ModInformation {

  public static final String MOD_ID = "Fluidity";
  public static final String MOD_NAME = "Fluidity";
  public static final String MOD_VERSION = "@VERSION@";
  public static final String MOD_DEPENDENCIES = "after:*";
  public static final String MOD_FINGERPRINT = "@FINGERPRINT@";
  public static final String MOD_PACKAGE = "com.robrit.fluidity";

  public static final String PROXY_CLIENT_LOCATION = MOD_PACKAGE + ".client.proxy.ClientProxy";
  public static final String PROXY_SERVER_LOCATION = MOD_PACKAGE + ".server.proxy.ServerProxy";

  public static final boolean DEBUG_MODE = false;
}

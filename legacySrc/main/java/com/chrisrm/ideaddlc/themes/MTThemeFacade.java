/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.chrisrm.ideaddlc.themes;

import com.chrisrm.ideaddlc.themes.models.MTThemeable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for the selected theme
 */
public interface MTThemeFacade {
  /**
   * The internal theme's color scheme
   *
   * @return
   */
  @NotNull
  String getThemeColorScheme();

  /**
   * The intrrnal theme
   *
   * @return
   */
  @NotNull
  MTThemeable getTheme();

  /**
   * The internal theme isDark
   *
   * @return
   */
  boolean getThemeIsDark();

  /**
   * The enum name
   *
   * @return
   */
  @NotNull
  String getName();

  /**
   * The internal theme name
   *
   * @return
   */
  @Nullable
  String getThemeName();

  /**
   * The internal theme id
   *
   * @return
   */
  @NotNull
  String getThemeId();

  /**
   * Icon
   *
   * @return
   */
  Icon getIcon();

  /**
   * The predefined accent color
   *
   * @return
   */
  String getAccentColor();

  /**
   * The extenral files color
   *
   * @return
   */
  Color getExcludedColor();

  /**
   * Order in the list
   *
   * @return
   */
  int getOrder();
}
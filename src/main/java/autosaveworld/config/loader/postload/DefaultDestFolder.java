/*
  This program is free software; you can redistribute it and/or
  modify it under the terms of the GNU General Public License
  as published by the Free Software Foundation; either version 3
  of the License, or (at your option) any later version.
  <p>
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  <p>
  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package autosaveworld.config.loader.postload;

import java.io.File;
import java.util.List;

public class DefaultDestFolder implements PostLoad {

    @SuppressWarnings("unchecked")
    @Override
    public void postLoad(Object value) {
        List<String> list = (List<String>) value;
        if (list.isEmpty()) {
            list.add(new File(".").getAbsoluteFile().getParent());
        }
    }

}
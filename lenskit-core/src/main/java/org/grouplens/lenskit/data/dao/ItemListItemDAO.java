/*
 * LensKit, an open source recommender systems toolkit.
 * Copyright 2010-2013 Regents of the University of Minnesota and contributors
 * Work on LensKit has been funded by the National Science Foundation under
 * grants IIS 05-34939, 08-08692, 08-12148, and 10-17697.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package org.grouplens.lenskit.data.dao;

import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.longs.LongSortedSet;
import org.grouplens.lenskit.collections.LongUtils;
import org.grouplens.lenskit.core.Shareable;
import org.grouplens.lenskit.util.io.LKFileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

/**
 * An item DAO that stores a precomputed list of items.
 *
 * @since 2.1
 * @author <a href="http://www.grouplens.org">GroupLens Research</a>
 */
@Shareable
public class ItemListItemDAO implements ItemDAO, Serializable {
    private static final long serialVersionUID = 1L;
    private final LongSortedSet itemSet;

    public ItemListItemDAO(Collection<Long> items) {
        itemSet = LongUtils.packedSet(items);
    }

    @Override
    public LongSet getItemIds() {
        return itemSet;
    }

    /**
     * Read an item list DAO from a file.
     * @param file A file of item IDs, one per line.
     * @return The item list DAO.
     * @throws IOException if there is an error reading the list of items.
     */
    public static ItemListItemDAO fromFile(File file) throws IOException {
        return new ItemListItemDAO(LKFileUtils.readIdList(file));
    }
}

package io.github.xjrga.potatosql.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Copyright (C) 2022 Jorge R Garcia de Alba &lt;jorge.r.garciadealba@gmail.com&gt;
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
public enum Connections {

    PRODUCTION("jdbc:hsqldb:file:data/database/production/potatosql;shutdowncompact=true", "sa", ""),
    TESTING("jdbc:hsqldb:file:data/database/testing/potatosql;shutdowncompact=true", "sa", ""),
    LOCALHOST("jdbc:hsqldb:hsql://localhost:9001/database", "sa", ""),
    MEM("jdbc:hsqldb:mem:temp", "sa", "");

    private Connection connection;
    private final String url;
    private final String user;
    private final String passwd;

    Connections(String url, String user, String passwd) {
        this.url = url;
        this.user = user;
        this.passwd = passwd;
    }

    public Connection get_connection() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection(url, user, passwd);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    @Override
    public String toString() {
        return name();
    }
}

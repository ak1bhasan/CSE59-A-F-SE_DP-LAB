class SqlQuery {
    private final String query;

    private SqlQuery(Builder builder) {
        this.query = "SELECT " + builder.select + " FROM " + builder.from + 
                    ( builder.where != null ? " WHERE " + builder.where : ";" );
    }

    public String getQuery() {
        return query;
    }

    public static class Builder {
        private String select = "*";
        private String from;
        private String where;

        public Builder from(String table) {
            this.from = table; 
            return this;
        }

        public Builder select(String columns) { 
            this.select = columns; 
            return this; 
        }

        public Builder where(String condition) { 
            this.where = condition; 
            return this; 
        }

        public SqlQuery build() {
            if( this.from == null ) throw new IllegalStateException("ERRROR : FROM required.");
            return new SqlQuery(this);
        }

    }

}


public class Ex3 {
    public static void main(String[] args) {
        SqlQuery query = new SqlQuery.Builder()
                        .select("name, registration_num")
                        .from("students")
                        .where("batch >= 10")
                        .build();

        System.out.println(query.getQuery());
    }
}

import pymongo



class Connection:
    """
    Represents a connection to a MongoDB database.
    """
    def __init__(self, db: str, host: str="localhost", port: int=27017):
        """
        Creates a Connection instance for database interaction.
        Valid DB name must be specified.

        Args:\n
            `db`   (str) : name of the database to connect to
            `host` (str) : host to connect to
            `port` (int) : port number to connect to
        """

        if not db: raise ValueError("Database name must be specified on creation.")
        self.host = host
        self.port = port
        self.conn = pymongo.MongoClient(url, port)
        self.db = self.conn[db]
    
    def set_db(self, name: str) -> None:
        """
        Sets the DB to be interacted with.

        Args:\n
            `name` (str): name of the database
        """
        self.db = self.conn[name]

class API:
    def __init__(self, conn):
        self.db = self.conn.db
        

    def get_category_products(self, category: str, kv_filter: dict={}) -> dict:
        """
        Returns items in the given category. Can optionally be filtered based 
        on a key/value specifier.

        Args:\n
            `category`   (str): name of the category to search for
            `kv_filter` (dict): key/value object to filter by
        """
        combined_filter = {k:v for k,v in kv_filter.items()}
        combined_filter["category"] = category
        return self.db.find(combined_filter)

    

    
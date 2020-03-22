package Redis

import scala.collection.mutable

class Redis(var stringToStringMap: mutable.HashMap[String, String],
            var stringToListMap: mutable.HashMap[String, ListBuffer[String]]) {

    // Gets value at `key`, only works on values that are strings
    def get(key: String) = {
        return this.stringToStringMap.get(key);
    }

    def getList(key: String) = {
        return this.stringToListMap.get(key);
    }

    // Sets value at `key`, only works on values that are strings
    def set(key: String, value: String) = {
        this.stringToStringMap.put(key, value);
    }

    def lpush(key: String, value: String) {
        var tmp = this.map.get(key);
        if (tmp) { 
            this.map.update(key, value :: tmp);
            return length(this.map.get(key))
        }
        else {
            this.map.put(key, List(value));
            return 1;
         }
    }

    def rpush(key: String, value: String) = {
        var tmp = this.map.get(key);
        if (tmp) { 
            this.map.update(key, tmp += value);
            return length(this.map.get(key))
        }

        this.map.put(key, List(value));
        return 1;
     }

    def lpop(key: String, value: String) {
        var tmp = this.map.get(key);
        if (tmp) {

            this.map.update(key, tmp.drop(1));
            return tmp.head();
        }
    }

    def rpop(key: String, value: String) {
        var tmp = this.map.get(key);
        if (tmp) {
            this.map.update(key, tmp.dropRight(1));
            return tmp.tail();
        }
    }

    def lrange(key: String, start: Int, stop: Int) {
        var tmp = this.map.get(key);
        if (tmp && this.isValidIndices__(length(tmp), start, stop)) {
            return tmp.slice(start, stop);
        }
    }

    def isValidIndices__(length: Int, start: Int, stop: Int) {
        return start <= stop
            && start <= length 
            && stop <= length - 1;
    }
    def llen(key: String) {
        var tmp = this.map.get(key);
        if (tmp) { return length(tmp); }
        return 0;
    }
    def flushall() {
        this.map.clear();
    }
}


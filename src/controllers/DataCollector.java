package controllers;

public class DataCollector {
    private final String directory;
    private final String extension;
    private final String byteStringTo;
    private final String byteStringWith;


    public static class Builder{
        private String directory;
        private String extension;
        private String byteStringTo;
        private String byteStringWith;

        public Builder setDirectory(String directory){
            this.directory = directory;
            return this;
        }

        public Builder setExtension(String extension){
            this.extension = extension.replace(".","").strip();
            return this;

        }

        public Builder setByteStringTo(String byteStringTo){
            this.byteStringTo = byteStringTo;
            return this;

        }

        public Builder setByteStringWith(String byteStringWith){
            this.byteStringWith = byteStringWith;
            return this;
        }

        public DataCollector build(){
            return new DataCollector(this);
        }

    }

    private DataCollector(Builder builder){
        directory = builder.directory;
        extension = builder.extension;
        byteStringTo = builder.byteStringTo;
        byteStringWith = builder.byteStringWith;
    }

    public String toString(){
        return "Dir: "+directory+" Ext: "+extension+" ByteStringTo: "+ byteStringTo +" ByteStringWith: "+byteStringWith;
    }

    public String getDirectory() {
        return directory;
    }

    public String getExtension() {
        return extension;
    }

    public String getByteStringTo() {
        return byteStringTo;
    }

    public String getByteStringWith() {
        return byteStringWith;
    }
}

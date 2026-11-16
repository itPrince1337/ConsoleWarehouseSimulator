package product.assembly.line.product;

import java.util.Objects;

import static java.util.Objects.hash;

public final class Product {
    private final String name;
    public Product(String name){
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    @Override
    public boolean equals(final Object otherObject){
        if(this == otherObject){
            return true;
        }
        if(otherObject == null){
            return false;
        }
        if(this.getClass() != otherObject.getClass()){
            return false;
        }
        Product otherProduct = (Product) otherObject;
        return Objects.equals(this.getName(), otherProduct.getName());
    }
    @Override
    public int hashCode(){
        return hash(this.getName());
    }
    @Override
    public String toString(){
        return "Name [" + this.name + "]";
    }

}

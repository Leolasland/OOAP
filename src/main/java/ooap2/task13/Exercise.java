package ooap2.task13;

class A extends Any {

    //метод публичен в родительском классе А и публичен в его потомке B;
    public String getA() {
        return "A";
    }

    //метод скрыт в родительском классе А и публичен в его потомке B;
    protected String getB() {
        return "B";
    }

    //метод публичен в родительском классе А и скрыт в его потомке B;
    //это невозможно, потому что в наследниках доступ у метода должны быть таким же или более открытым:
    // например, это метод getB(), который скрыт в родительском классе и публичен в наследнике
    public String getC() {
        return "C";
    }

    //метод скрыт в родительском классе А и скрыт в его потомке B.
    protected String getD() {
        return "D";
    }
}

class B extends A {

    @Override
    public String getA() {
        return super.getA() + " from B class";
    }

    @Override
    public String getB() {
        return this.getB() + " from B class";
    }

    // Ошибка: attempting to assign weaker access privileges ('private'); was 'public'
//    @Override
//    private String getC() {
//        return super.getC() + " from B class";
//    }

    @Override
    protected String getD() {
        return super.getD() + " from B class";
    }
}

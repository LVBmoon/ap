package src.kargahpishrafte;

public class Session2_atClass {
    public static void main(String[] args){

        University university1 = new University("Neverland Academy of Eternal Youth","Neverland");
        Student student1 = new Student(403463127,"LVB",university1);
        Book book1 = new Book ("misery","me",1384,200);
        Cars car1 = new Cars("Tesla","Model S",2023);

        book1.printInfos();
        System.out.println("\n" + car1.getBrand());
        System.out.println(student1); // Calls Student.toString()
        System.out.println(university1);
        System.out.println("Student's University Address: " + student1.getUniversity().getUniAddress());

        }

    public static class Student{
        private int id;
        private String name;
        private University university;

        public Student(int id, String name, University university) {
            if(university == null || name == null || id < 0){
                throw new IllegalArgumentException("Invalid Student information!");
            }
            this.id = id;
            this.name = name;
            this.university = university;
        }

        public int getId(){
            return id;
        }
        public String getName(){
            return name;
        }
        public University getUniversity(){
            return university;
        }

        @Override
        public String toString(){
            return "Student name :" + name + " id :" + id + " university :" + university.toString();
            //return "Student name :" + name + " - id :" + id + " - university :" + getUniversity().getUniName();
        }
    }

    public static class University{
        private String uniName;
        private String uniAddress;

        public University(String uniName, String uniAddress){
            this.uniName = uniName;
            this.uniAddress = uniAddress;
        }

        public String getUniName() {
            return uniName;
        }
        public String getUniAddress() {
            return uniAddress;
        }

        @Override
        public String toString() {
            return "University: " + uniName + " (" + uniAddress + ")";
        }
    }

    public static class Book{
        private String title;
        private String author;
        private int year;
        private int pages;

        public Book(String title, String author, int year, int pages){
            if(title == null || author == null || year < 0 || pages < 0){
                throw new IllegalArgumentException("Invalid book information!");
            }
            this.title = title;
            this.author = author;
            this.year = year;
            this.pages = pages;
        }

        public void printInfos(){
            System.out.println("Title: " + title);
            System.out.println("Author: " + author);
            System.out.println("Year: " + year);
            System.out.println("Pages: " + pages);
        }

        public String getTitle(){
            return title;
        }
        public String getAuthor(){
            return author;
        }
        public int getYear(){
            return year;
        }
        public int getPages(){
            return pages;
        }

    }

    public static class Cars{
        private int year;
        private String brand;
        private String model;

        public Cars(String brand, String model, int year){
            if (brand == null || model == null || year < 1886){
                throw new IllegalArgumentException("Invalid car information!");
            }
            this.brand = brand;
            this.model = model;
            this.year = year;
        }
        public Cars(String model){
            this.brand = "Tesla";
            this.model = model;
            this.year = 1999;
        }
        /*or :
         public Cars(String model){
         this.("Tesla",model,1999)
         }
         */

        public String getBrand(){
            return brand;
        }
        public String getModel(){
            return model;
        }
        public int getYear(){
            return year;
        }
    }

}

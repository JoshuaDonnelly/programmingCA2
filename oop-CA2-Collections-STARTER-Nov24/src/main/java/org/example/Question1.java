package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Your Name: Joshua Donnelly
 *  Class Group: SD2a
 */
public class Question1 {    // Interfaces
    public static void main(String[] args) {
        System.out.println("Question 1");

                ContainerManager manager = new ContainerManager();

                Box box = new Box(2.0, 3.0, 4.0, 5.0);
                Cylinder cylinder = new Cylinder(10.0, 4.0, 8.0);
                Toblerone toblerone = new Toblerone(6.0, 5.0, 4.0);

                manager.add(box);
                manager.add(cylinder);
                manager.add(toblerone);

                System.out.println("Total Weight: " + manager.totalWeight());
                System.out.println("Total Rectangular Volume: " + manager.totalRectVolume());

                List<IMeasurableContainer> containers = manager.getAllContainers();
                for (IMeasurableContainer container : containers) {
                    if (container instanceof Box) {
                        Box b = (Box) container;
                        System.out.println("Box - Length: " + b.getLength() + ", Width: " + b.getWidth() + ", Depth: " + b.getDepth() + ", Weight: " + b.getWeight());
                    } else if (container instanceof Cylinder) {
                        Cylinder c = (Cylinder) container;
                        System.out.println("Cylinder - Height: " + c.getHeight() + ", Diameter: " + c.getDiameter() + ", Weight: " + c.getWeight());
                    } else if (container instanceof Toblerone) {
                        Toblerone t = (Toblerone) container;
                        System.out.println("Toblerone - Length: " + t.getLength() + ", Side Length: " + t.getSideLength() + ", Weight: " + t.getWeight());
                    }
                }
            }
        }

        interface IMeasurableContainer {
            double weight();
            double rectangularVolume();
        }

         class Box implements IMeasurableContainer {
            private double length;
            private double width;
            private double depth;
            private double weight;

            public Box(double length, double width, double depth, double weight) {
                this.length = length;
                this.width = width;
                this.depth = depth;
                this.weight = weight;
            }

            public double getLength() {
                return length;
            }

            public double getWidth() {
                return width;
            }

            public double getDepth() {
                return depth;
            }

            public double getWeight() {
                return weight;
            }

            @Override
            public double weight() {
                return weight;
            }

            @Override
            public double rectangularVolume() {
                return length * width * depth;
            }
        }

         class Cylinder implements IMeasurableContainer {
            private double height;
            private double diameter;
            private double weight;

            public Cylinder(double height, double diameter, double weight) {
                this.height = height;
                this.diameter = diameter;
                this.weight = weight;
            }

            public double getHeight() {
                return height;
            }

            public double getDiameter() {
                return diameter;
            }

            public double getWeight() {
                return weight;
            }

            @Override
            public double weight() {
                return weight;
            }

            @Override
            public double rectangularVolume() {
                double radius = diameter / 2.0;
                return diameter * diameter * height; // Bounding box for a cylinder
            }
        }

        // Pyramid class
         class Toblerone implements IMeasurableContainer {
            private double length;
            private double sideLength;
            private double weight;

            public Toblerone(double length, double sideLength, double weight) {
                this.length = length;
                this.sideLength = sideLength;
                this.weight = weight;
            }

            public double getLength() {
                return length;
            }

            public double getSideLength() {
                return sideLength;
            }

            public double getWeight() {
                return weight;
            }

            @Override
            public double weight() {
                return weight;
            }

            @Override
            public double rectangularVolume() {
                return length * sideLength * sideLength;
            }
        }
    class ContainerManager
        {
        private List <IMeasurableContainer> containers = new ArrayList <> ();

        void add(IMeasurableContainer container) {
            containers.add(container);
        }
        double totalWeight() {
            double totalWeight = 0;
            for (IMeasurableContainer container : containers) {
                totalWeight += container.weight();
            }
            return totalWeight;
        }
        double totalRectVolume()
        {
            double totalVolume = 0;
            for (IMeasurableContainer container : containers) {
                totalVolume += container.rectangularVolume();
            }
            return totalVolume;
        }
        private void clearAll ()
        {
            containers.clear();
        }
        public List<IMeasurableContainer> getAllContainers()
        {
            return new ArrayList<>(containers);
        }
    }






# image-labeller
A program for appending image information to a spreadsheet

A simple graphical program which loads in a spreadsheet in format;

sku | name | image | image info

The program will read in each row of the file and attempt to load the image path (relative to the program), it has fields for the user to then change or append the image info column data for each row.

Initial use case was for image alternative text related to products on e-commerce website but it has been created so that the columns could theoretically be representations of any text data.

Dependencies: Apache POI - Java API for Microsoft documents - https://poi.apache.org/

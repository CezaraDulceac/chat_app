# CHAT Supplementary Specification

# Introduction
This document describe non-functional requirements of the CHAT system (availability, performance, security, testability, usability) and design constrains. 

# Non-functional Requirements

CHAT system was developed using Java language combined with a relational database (SQLite). The app use the Model-View-Controller architectural pattern and the Factory Method design pattern. 

## Availability
The app works as a desktop application. In the future it will be developed for mobile platforms too.

## Performance

The response time of the app is short because it was developed in a local area. 

## Security

The login is secured, every user need to have an unique username and the password is hidden for everybody. 

## Testability

Username and password testing. 

## Usability

The application is easy to use for any kind of person from beginner to expert in the field of technology. A well-designed interface guides the user through the easiest process of creating a new account and creating new conversations or seeing the old ones. 

# Design Constraints

In the development of CHAT we chose the next design constraints:

-using the Model-View-Controller architectural  pattern.

-using the Factory Method Pattern.

-using only Java combined with SQLite

-every kind of user need to have a username (unique) and password(hidden to everyone)





# Resources

* http://www.upedu.org/process/gdlines/md_srs.htm
* Example of Supplementary Specification: http://csis.pace.edu/~marchese/SE616_New/Samples/Example%20%20Supplementary%20Specification.htm

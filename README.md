# My Personal Project

## Reading time tracker

**Proposed features:**
- ways to store a text's name, associated genre, and
word count (provided by the user)
- ways to track multiple texts at the same time
- in-app timer
- calculation of *estimated* reading speed using logged time
- calculation of *estimated* reading time for given word count and genre, using
the calculated reading speed from other tesxts
- options to manually adjust logged time

**Users:**

- people interested in improving reading speed
- people interested in estimating time needed to complete
reading a given text
- people interested in tracking how much time they spend reading

**Why:**

* reading/writing is one of my hobbies, and something that I wish
I had when reading was a way to keep track of how long it took me to finish
a particular book or other text, and how this time frame could vary depending on the genre.
Additionally, I could see this being useful to estimate the time that should be alloted
to reading a specific text.

## User Stories

- As a user, I want to be able to add and 
remove texts from a list of texts currently be tracked.
- As a user, I want to know the total time I spent reading
on a specific text.
- As a user, I want to know my mean reading speed on a text.
- As a user, I want to know my mean reading
speed over multiple texts.
- As a user, I want to be able to mark when I have finished reading
a text, so that the reading speed can be calculated.
- As a user, I want to provide a category for a text
(graphic novel, novel, short story, non-fictional, class reading etc.)
- As a user, I want to be able to estimate the time 
needed to finish reading a text using data 
from selected previously completed texts.
- As a user, I want to be able to save my texts, with their word counts, genres, and recorded times, to a file
- As a user, I want to be able to load my texts (with their details) from a file when launching the app, allowing
recording to resume


## Instructions for Grader (GUI)
- Load data:
  - Upon running ReadingTimerAppGUI, select either "Load saved data" to load previous data, or "skip" to start a new 
  save
- Add a new Text:
  - To add a new Text, click the "Manage Texts" option in the menu bar, then 
  click on "Add Text"
  - Enter a title and click submit
  - Enter a word count (must be an integer) and click submit
  - Select one of the genre options from the buttons shown
- Remove a Text:
  - Select the text to be deleted
  - Click on "Manage Texts" in the menu bar and select delete
- Save a Text:
  - Click on "Manage Texts" and select "Save Data"
- Visual component:
    - When timer is running, a timer icon is shown
## Phase Four Task 2

```Changed genre for test 5 from "Any other non-fiction material" to "Non-fiction article".
Fri Apr 07 12:22:20 PDT 2023
Added test 5 to list.
Fri Apr 07 12:22:24 PDT 2023
Started timer for test 5
Fri Apr 07 12:23:15 PDT 2023
Ended timer for test 5
Fri Apr 07 12:23:18 PDT 2023
Started timer for test 5
Fri Apr 07 12:23:42 PDT 2023
Ended timer for test 5
Fri Apr 07 12:24:03 PDT 2023
Started timer for test 5
Fri Apr 07 12:24:34 PDT 2023
Ended timer for test 5
Fri Apr 07 12:24:37 PDT 2023
Marked test 5 as complete.
```

## Phase Four Task 3

As I worked on building this program, there were several decisions that I made early on that I think are poor
design choices in the long run. Some of the refactoring I would consider is making the ListOfText iterable, since
several parts of the program require dealing with the Texts inside the ListOfText (currently being supplied by getters). 
Another change I would make is making an abstract class for my GUI
buttons, similar to the abstract class I made for my menu items. Due to the time constraint I made the buttons with
the goal of making them work quickly, so some of the methods I used to get it working are probably 
unnecessarily complicated or more tightly coupled than they should be. When refactoring I would like to re-examine them
and adjust how they work as well as incorporating the abstract class.


I also initially considered creating a subclass for texts specifically in
online formats to support including links, which was ultimately scrapped since getting the subclass to work with
the rest of the program (and ListOfText) was too time-consuming. I think with some
refactoring it would be possible to include it again, perhaps by including another 
list inside an iterable ListOfText.

## Citations

- Parts of the persistence package are sourced from the JsonSerialization demo
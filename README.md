This is the initial solution for a puzzle to find an available meeting room depending on a request.

Points to be solved (still):

- Does not split meeting into multiple rooms
- Does not contain unit tests
- Does not load from TXT
- Validation of rooms and input (the program is considering that the input and the search are both valid)

Things that can be improved:

- Data loaded to database (would be so much better)
- Modularization (separated packages for parsing, finding)

----

how you solved the problem?

- Parsing the rooms into objects:
    - Floor that contain rooms with its capacity, and the available times.
- Checking for rooms on the same floor, if not found, checking on nearest floors (up and down)

how it would behave based on the different parameters?
(number of team members, longer meeting times, many rooms with random booking times).

- By parsing the rooms and the search input to objects, we make them available to be used by the search logic.

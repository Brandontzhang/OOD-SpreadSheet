# OOD-SpreadSheet
## TODO
### Brandon
- Convert all user access data types to strings
- Figure out how to hold cell input
        - evaluate cell in the spreadsheet vs evaluating the cell in the cell constructor
        
 ### Josh
- expansion of spreadsheet arraylist
        - new function to allocate arraylist cell (function increaseSize)
- write tests
  
- first implementation of region cells

## Design

### Model/SpreadSheet
Fields:
- Contains a 2D arraylist of Cells

Public Methods:
- Cell getCell(String coordinate) -> returns a cell given its coordinates
- Coord getCoord(Cell) -> returns the coordinate of a cell
- list(Cells) getRegionCells(String coordinate1, String coordinate2) -> returns a list of cells in a given area between the two cells
- void updateCell(String coordinate, String content) -> updates the given Cell with the string

Private Methods:
- Cell createNewCell(Coord) -> creates a new cell at the coordinate if the user hasn't tried to access it before and we haven't made it yet

### Cells
Fields
- Content (Sexpression, a list of Sexpressions is also an Sexpression)
we could consider changing this to be the evaluated expression or also have two separate fields:
- container holding inputs
- outer most function call
        
Public method
- String evaluateCell() -> evaluates the cell using visitor objects (can we pull this into the model?)

### Sexp
Can be one of:
  - blank
  - value
    - boolean
    - doubles
    - String
    - list of Sexp
    - NOTE: Formula/list cannot refer to itself

Model
- support references to individual as well as groups of cells
- support various formulas (create classes to be called upon)
    - formulas are classes that have a function call
    - for cells without a function, give just regular output function?
        - at least sum, product, and <, plus another one which has return type string
- support editing of cells

Need to create a builder class that takes in a list of cells and creates the spread sheet

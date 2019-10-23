### OOD-SpreadSheet

## Design

# Model/SpreadSheet
Fields:
- Contains a 2D array of Cells

Public Methods:
- list<Cell>   getRegionCells(Coord, Coord)    ->    returns a list of cells in a given area between the two cells
- void         updateCell(Coord, String)       ->    updates the given Cell with the string
- Cell         getCell(Coord)                  ->    returns a cell given its coordinates
- Coord        getCoord(Cell)                  ->    returns the coordinate of a cell

Private Methods:
- Cell         createNewCell(Coord)            ->    creates a new cell at the coordinate if the user hasn't tried to access                                                        it before and we haven't made it yet

# Cells
Fields
- Content (Sexpression, a list of Sexpressions is also an Sexpression)
        
Public method
- Sexp         evaluateCell()                  ->    evaluates the cell using visitor objects (can we pull this into the                                                            model?)

# Sexp
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

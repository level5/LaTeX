function isTriangle(a, b, c) {

  return false;
}


function buildRowText(index, character) {
  // Do your magic :)
  return "| | | | | | | | |".slice(0, index * 2) + character + "| | | | | | | | |".slice(index * 2 + 1);
}

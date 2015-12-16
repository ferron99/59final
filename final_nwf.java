////Final
//// Nick Ferro

int many=9;
Squid school[]=  new Squid[many];
String names[]=  { "Otto", "Nono", "Deca", "Ariel", "Ursala", "Squidy", "Charles", "Nanner", "Doc" };
float spacing;
SData sdatas[]= new SData[many];
Boat fleet[]= new Boat[5];
BData bdatas[]= new BData[fleet.length];
Lobster nwf[]= new Lobster[4];
LData ldatas[]= new LData[nwf.length];
Button b;
float surface;
float moonX=0, moonY=100;
int score=0;
int frame=0;
boolean pause = false;

//// SETUP:  size & reset.
void setup() {
  size( 1200, 600 );
  spacing=  width/(many+1);
  reset();
}
// Constuct squid(s).
void reset() {
  surface=  height/3;
  moonY=  surface/3;
  moonY=  random( 200, surface+200 );
  // Many squids.
  float x=  spacing;
  for (int i=0; i<many; i++ ) {
    school[i]=  new Squid( names[i], x );
    x += spacing;
  }
  //Squid Data
  for (int i=0; i<many; i++) {
    sdatas[i] = new SData();
    sdatas[i].id = i;
  }
  
  //Boats
  for (int i=0; i<fleet.length; i++){
    fleet[i]= new Boat();
    fleet[i].dx = random( 3, 5 );
  }
  fleet[0].name = "Boat";
  fleet[1].name = "Ship";
  fleet[2].name = "Raft";
  fleet[3].name = "Barge";
  fleet[4].name = "Bounty";
  //Boat Data
  for (int i=0; i<fleet.length; i++) {
    bdatas[i] = new BData();
    bdatas[i].id = i;
  }
  //lobsters
  float lobspace = 50;//(height-surface)/5; 
  for (int i=0; i<nwf.length; i++){
    nwf[i] = new Lobster(surface+((i)*spacing));
  }
  nwf[0].name= "Ralph";
  nwf[1].name= "Joe";
  nwf[2].name= "Susan";
  nwf[3].name= "Fred";
  //Loster Data
  for (int i=0; i<nwf.length; i++) {
    ldatas[i] = new LData();
    ldatas[i].id = i;
  }
  b = new Button(50,50);
  b.words = "HI/LO";
}


//// NEXT FRAME:  scene, action
void draw() {
  scene();
  show();
  if (pause == true) {
    boatReport( 50, fleet.length+1 );
    fishReport( surface+50, sdatas, school.length);
    lobsterReport( ldatas, nwf.length );
  }else{ action(); }
  dataTrack();
  messages();
}


void messages() {
  fill(0);
  textSize(12);
  text( "Nick Ferro: Final", 10, height-10 );
  text( "Press 'c' to pause, press 'v' to unpause", width/2, height-10);
  if (score>0) text( "SCORE:  "+score, width*3/4, 20 );  
}

//// METHODS TO MOVE & DRAW.
void scene() {
  background( 50,150,200 );      // Dark sky.
  // Moon
  if (moonX > width+100) {
    moonX=  -100;
    moonY=  random( 100, surface+50 );
  }
  moonX += 1;
  fill( 200,150,50 );
  noStroke();
  ellipse( moonX, moonY-150*sin( PI * moonX/width ), 40,40 );
  // Dark water.
  fill( 0,100,50 );
  noStroke();
  rect( 0,surface, width, height-surface );
  sideTriangles();  
}
// draws triangles on the side of screen
void sideTriangles(){
  for (float y= surface+15; y < height; y+=40){
    fill(255,0,0);
    triangle(width-50, y, width-35, y-15, width-35, y+15);
  }
}

void action() {
  // Move squids & boats.
  for (int i=0; i<many; i++ ) {
    school[i].move();
  }
  for (int i=0; i<fleet.length; i++){
    fleet[i].move();
  }
  for (int i=0; i<nwf.length; i++){
    nwf[i].move();
  }
  b.buttonDisplay();   //button display
  frame+=1;            //frame count for lobster animation
  lysrt();             //attempt to sort lobster data by y coor for the highlighting
  b.hILO();            //displays highlighting
}

void dataTrack(){
  for (int i=0; i<many; i++){
    sdatas[i].x = school[sdatas[i].id].x;
    sdatas[i].y = school[sdatas[i].id].y;
    sdatas[i].dy = school[sdatas[i].id].dy;
    sdatas[i].legs = school[sdatas[i].id].legs;
    sdatas[i].name = school[sdatas[i].id].name;
  }
  //track data for boats
  for (int i=0; i<fleet.length; i++){
    bdatas[i].x = fleet[bdatas[i].id].x;
    bdatas[i].dx = fleet[bdatas[i].id].dx;
    bdatas[i].cargo = fleet[bdatas[i].id].cargo;
    bdatas[i].name = fleet[bdatas[i].id].name;
  }
  // track data for lobsters
  for (int i=0; i<nwf.length; i++){
    ldatas[i].x = nwf[ldatas[i].id].x;
    ldatas[i].y = nwf[ldatas[i].id].y;
    ldatas[i].dx = nwf[ldatas[i].id].dx;
    ldatas[i].dy = nwf[ldatas[i].id].dy;
    ldatas[i].name = nwf[ldatas[i].id].name;
  }
}

//// Display objects
void show() {
  float x=  spacing;
  for (int i=0; i<many; i++ ) {
    school[i].x=  x;
    x += spacing;
    school[i].show();
  }
  for (int i=0; i<fleet.length; i++){
    fleet[i].show();
  }
  for (int i=0; i<nwf.length; i++){
    nwf[i].show();
  }
}

//sorts LData by ascending y value
void lysrt(){
  for( int j=0; j<10; j++) {
  for (int i=0; i<nwf.length-1; i++){
    if(ldatas[i].y > ldatas[i+1].y){
      lswap(i, i+1);
    }
    if(ldatas[i].y > ldatas[i+1].y){
      lswap(i, i+1);
    }
    if(ldatas[i].y > ldatas[i+1].y){
      lswap(i, i+1);
    }
   }
  }
} 


//swaps LData ids
void lswap( int j, int k ) {
  int tmpid;
  tmpid=  ldatas[k].id;
  ldatas[k].id=  ldatas[j].id;
  ldatas[j].id=  tmpid;
}

//// SUMMARIES:  List all objects in the array.
// Display the properties of each object in the array.
void boatReport( float top, int many ) {
  fill(255,200,200);
  rect( 50,top, width-100, 50 + 20*many );
  float x=70, y=top+20;
  // Labels.
  fill(150,0,0);
  text( "BOAT", x+20, y );
  text( "cargo", x+70, y );
  text( "x", x+110, y );
  text( "dx", x+205, y );
  fill(0);
  //
  for (int i=0; i<fleet.length; i++) {
    y += 15;    // Next line.
    text( i+1, x, y );
    text( bdatas[i].name, x+20, y );
    text( bdatas[i].cargo, x+70, y );
    text( bdatas[i].x, x+100, y );
    text( bdatas[i].dx, x+200, y );
  }
  text("Hold 'b' to sort by position (x)", width/2, top+20);
  text("Hold 'd' to sort by speed (dx)", width/2, top+35);
  text("Hold 'f' to sort by greatest cargo", width/2, top+50);
}

//sort bdata list into ascending x pos
void bxsrt(){
  for( int j=0; j<fleet.length; j++) {
  for (int i=0; i<fleet.length-1; i++){
    if(bdatas[i].x > bdatas[i+1].x){
      bswap(i, i+1);
    }
   }
  }
}

void bdxsrt(){
  for( int j=0; j<fleet.length; j++) {
  for (int i=0; i<fleet.length-1; i++){
    if(bdatas[i].dx > bdatas[i+1].dx){
      bswap(i, i+1);
    }
   }
  }
}

void bcargosrt(){
  for( int j=0; j<fleet.length; j++) {
  for (int i=0; i<fleet.length-1; i++){
    if(bdatas[i].cargo < bdatas[i+1].cargo){
      bswap(i, i+1);
    }
   }
  }
}



//swaps boat data ids
void bswap( int j, int k ) {
  int tmpid;
  tmpid=  bdatas[k].id;
  bdatas[k].id=  bdatas[j].id;
  bdatas[j].id=  tmpid;
}

void fishReport( float top, SData[] a, int many ) {
  fill(255,255,200);
  rect( 50,top, width-100, 50 + 20*many );
  float x=70, y=top+20;
  // Labels.
  fill(150,0,0);
  text( "FISH", x+20, y );
  text( "legs", x+70, y );
  text( "x", x+105, y );
  text( "y", x+205, y );
  text( "dy", x+305, y );
  fill(0);
  for (int i=0; i<many; i++) {
    y += 15;    // Next line.
    text( i+1, x, y );
    text( a[i].name, x+20, y );
    text( a[i].legs, x+70, y );
    text( a[i].x, x+100, y );
    text( a[i].y, x+200, y );
    text( a[i].dy, x+300, y );
  }
  text("Hold 'x' to sort by position (x)", width/2, top+20);
  text("Hold 'y' to sort by height (y)", width/2, top+35);
  text("Hold 's' to sort by speed(dy)", width/2, top+50);
  text("Hold 's' to sort by number of legs", width/2, top+65);
}


void lobsterReport( LData[] a, int many){
  fill(255,255,200);
  rect(width/2, height/2+50, width/2-100, height/2-100);
  float x = width/2+20; 
  float y= height/2+70;
  fill(150,0,0);
  text( "Lobster", x+20, y );
  text( "x", x+70, y );
  text( "y", x+135, y );
  text( "dx", x+205, y );
  text( "dy", x+305, y );
  for (int i=0; i<many; i++) {
    y += 15;    // Next line.
    text( i+1, x, y );
    text( a[i].name, x+20, y );
    text( a[i].x, x+70, y );
    text( a[i].y, x+130, y );
    text( a[i].dx, x+200, y );
    text( a[i].dy, x+300, y );
  }
}

void sxsrt(){
  for( int j=0; j<many; j++) {
  for (int i=0; i<many-1; i++){
    if(sdatas[i].x > sdatas[i+1].x){
      sswap(i, i+1);
    }
   }
  }
} 

void sysrt(){
  for( int j=0; j<many; j++) {
  for (int i=0; i<many-1; i++){
    if(sdatas[i].y > sdatas[i+1].y){
      sswap(i, i+1);
    }
   }
  }
} 

void sdysrt(){
  for( int j=0; j<many; j++) {
  for (int i=0; i<many-1; i++){
    if(sdatas[i].dy > sdatas[i+1].dy){
      sswap(i, i+1);
    }
   }
  }
}    

void slegssrt(){
  for( int j=0; j<many; j++) {
  for (int i=0; i<many-1; i++){
    if(sdatas[i].legs > sdatas[i+1].legs){
      sswap(i, i+1);
    }
   }
  }
}    
    
//swaps squid data ids
void sswap( int j, int k ) {
  int tmpid;
  tmpid=  sdatas[k].id;
  sdatas[k].id=  sdatas[j].id;
  sdatas[j].id=  tmpid;
}    

//// EVENT HANDLERS:  keys, clicks ////
void keyPressed() {
  if (key == 'r') reset();
  // Send a squid to the bottom!
  if (key == '0') school[0].bottom(); 
  if (key == '1') school[1].bottom(); 
  if (key == '2') school[2].bottom(); 
  if (key == '3') school[3].bottom(); 
  //// Send highest to bottom.
  if (key == 'h') {
    int k=0;
    for (int i=1; i<many; i++ ) {
      if (school[i].y < school[k].y) k=i;           // k is index of highert.
    }
    school[k].bottom();     
  }
  if(key == 'b'){
    //for(int i=0; i<10; i++){
    bxsrt();
    //}
  }
  if(key == 'd'){
    bdxsrt();
  }
  if(key == 'f'){
    bcargosrt();
  }
  if(key == 'x'){
    sxsrt();
  }
  if(key == 'y'){
    sysrt();
  }
  if(key == 's'){
    sdysrt();
  }
  if(key == 'l'){
    slegssrt();
  }
  
  // Cheat codes:
  //// Send all to top or bottom.
  //if (key == 'b') {
  //  for (int k=0; k<many; k++ ) {
  //    school[k].bottom();     
  //  }
  //}
  if (key == 't') {
    for (int k=0; k<many; k++ ) {
      school[k].y=  surface+10;
      school[k].dy=  -0.1  ;
    }
  }
  if (key == 'c') pause = true;
  if (key == 'v') pause = false;
}

void mousePressed(){
  b.buttonHILO();
}



//// OBJECTS ////

class Squid {
  float x,y;        // Coordinates
  float dx=0,dy=0;  // Speed
  float w=40,h=40;
  int legs=10;      // Number of legs.
  String name="";
  float r,g,b;      // Color.
  int count=0;
  //// CONSTRUCTORS ////
  Squid( String s, float x ) {
    this.name=  s;
    this.x=x;
    bottom();
    // Purplish
    r=  random(100, 255);
    g=  random(0, 100);
    b=  random(100, 250);
  }
  //// Start again at bottom.  (Random speed.)
  void bottom() {
    y=  height - h;
    dy=  -random( 0.1, 0.9 );
    legs=  int( random(1, 10.9) );
  }
  //// METHODS:  move & show ////
  void move() {
    x += dx;
    y += dy;
    if (y>height) { 
      bottom();
      count++;
    }
    else if (y<surface) { 
      dy=  -3 * dy;        // Descend fast!
    }
  }
  //// Display the creature.
  void show() {
    fill(r,g,b);
    stroke(r,g,b);
    ellipse( x,y, w,h );         // round top
    rect( x-w/2,y, w,h/2 );      // flat bottom
    fill(255);
    float blink=10;
    if ( y%100 > 80) blink=2;
    ellipse( x,y-h/4, 10, blink );     // eye
    // Legs
    fill(r,g,b);                 // legs.
    float legX=  x-w/2, foot=0;
    foot=  dy>=0 ? 0 : (y%47 > 23) ? 5 : -5;
    strokeWeight(3);
    for (int i=0; i<legs; i++) {
      line( legX, y+h/2, legX+foot, 20+y+h/2 );
      legX += w / (legs-1);
    }
      strokeWeight(3);
    fill(200,200,0);
    text( name, x-w/2, y-10+h/2 );
    fill(0);
    text( legs, x+2-w/2, y+h/2 );
    fill(255);
    if (count>0) text( count, x, y+h/2 );
  }
  //// Return true if near
  boolean hit( float xx, float yy ) {
    return dist( xx,yy, x,y ) < h;
  }
}

class SData{
  float x,y,dy;
  int id, legs;
  String name; 
  
  SData(){
    
  }
}


class Boat {
  String name="";
  float x=0, y=surface, dx=5;
  int cargo=0, caught=0;
  
  
  void move() {
    //// Fish before move:  check each squid.
    int caught=0;
    for (int i=0; i<many; i++ ) {
      if (school[i].hit( x, surface )) {
        caught += school[i].legs;
      }
    }
    cargo += caught;    
    //// Now, move the boat.
    x += dx;
    if (caught>0) x += 2*dx;      //  Jump after catch.
    if (x<0) {
      score += cargo;            // Add cargo to global score.
      cargo=0;
      dx = random( 1, 5 );      // Variable boat speed.
    }
    if (x>width)  {
      dx = -random( 0.5, 3 );    // Slower return.
    }
  }
  //// Draw the boat.
  void show() {
    // Boat.
    fill(255,0,0);
    noStroke();
    rect( x, surface-20, 50, 20 );
    if (dx>0)   triangle( x+50,surface, x+50,surface-20, x+70,surface-20 );
    else        triangle( x,surface, x,surface-20, x-20,surface-20 );
    rect( x+12, surface-30, 25, 10 );      // Cabin.
    fill(0);
    rect( x+20, surface-40, 10, 10 );      // Smokestack.
    // Display name & cargo.
    fill(255);
    text( name, x+5, surface-10 );
    fill(0);
    if (cargo>0) text( cargo, x+20, surface );
    // Smoke
    fill( 50,50,50, 200 );
    ellipse( x +20 -10*dx, surface-50, 20, 20 );
    ellipse( x +20 -20*dx, surface-60, 15, 10 );
    ellipse( x +20 -30*dx, surface-70, 8, 5 );
  }    
}

class BData { 
  float x, dx;
  String name; 
  int cargo, id;
  
  BData(){
  }
}

class Lobster {
  float x,y,dx,dy;
  float h = 30;
  String name=" ";
  
  Lobster(float yin){
    x= 75;
    y= yin;
    dx= random(1,3);
    dy= .5;
  }
  
  void move(){
    x+=dx;
    y+=dy;
    if(x<0 || x>width){
      dx*=-1;
    }
    if(y<surface || y>height){
      dy*=-1;
    }
     for (int i=0; i<many; i++ ) {
      if (school[i].hit( x, y )) {
        school[i].dy= 10;
      }
    }
  }
  
  void show(){
    fill(255,0,0);
    ellipse(x,y,50,25);
    stroke(255,0,0);
    if(dx>0){
      if(frame/15 % 2 == 0){
        line(x,y, x+30, y+10);
        line(x,y, x+30, y-10);
      }else if(frame/15 % 2 == 1){
        line(x,y, x+30, y+17);
        line(x,y, x+30, y-17);
      }
    } else {
      if(frame/15 % 2 == 0){
        line(x,y, x-30, y+10);
        line(x,y, x-30, y-10);
      }else if(frame/15 % 2 == 1){
        line(x,y, x-30, y+17);
        line(x,y, x-30, y-17);
      }
    }
    fill(0);
    text(name, x-15 ,y);
  }
   boolean hit( float xx, float yy ) {
    return dist( xx,yy, x,y ) < h;
  }
}

class LData{
  float x,y,dx,dy;
  int id;
  String name;
  
  LData(){
  }
}

class Button {
  //PROPERTIES
  float x,y;
  String words = " ";
  boolean hilight;
  //CONSTRUCTOR
  Button(float tempX, float tempY) {
    x = tempX;
    y = tempY;
    hilight = false;

  }
  //METHODS
  void buttonDisplay(){
    fill(0);
    strokeWeight(4);
    stroke(255);
    rectMode( CORNER );
    rect(x, y, 80, 40);
    fill(255);
    text( words, x+15, y+20);
  }
  
  void buttonHILO(){
    if (mouseX >x && mouseX<x+80 && mouseY>y && mouseY<y+40){
    hilight = true;
  }
  }
  // highlights the hi and lo lobster
  void hILO(){
    if (hilight == true){
      noFill();
      stroke(255);
      ellipse(ldatas[0].x, ldatas[0].y, 51 ,26);
      ellipse(ldatas[3].x, ldatas[3].y, 51 ,26);
      text("HI", 75, 125);
      text("LO", 75, 150);
      text(ldatas[0].name, 100,125);
      text(ldatas[3].name, 100,150);
    }
  }

}

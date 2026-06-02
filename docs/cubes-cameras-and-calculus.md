# Cubes, Comments, Cameras, and Calculus
So, after getting the first Triangles (coloured and textured) working, it was time to dive into 3D rendering. This would be
quite the step-up for myself, as it would mean having to handle objects with a ridiculous amount of vertices. Then, I would
need to find a way to easily move these massive arrays around the "render space", as I colloquially called it in my head,
simultaneously.

I will admit, before I went in, I was a bit nervous, as I was sure this is where I was going to "bounce off" of OpenGL,
if anywhere. I have never been more wrong in my entire life... so far haha! 

## Cubes and Comments
So, first things first, was I needed to make a Cube. This is something that I was not looking forward to as, due to the 
concepts learned in previous chapters, I had a rough idea in my head of what I was going to see next in the tutorial content.
Indeed, I had a mixture of satisfaction and dread as I saw the example vertices array of the cube. Satisfaction, as it was 
exactly how I thought it would be, hence being an indicator I had actually learned the previous concepts correctly. Dread,
because it _was exactly how it thought it would be_ ... specifically, this monstrosity... 

```java
 float[] cubeVertices = {
            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f, 0.0f,   0.0f, 0.0f,
            0.5f, -0.5f, -0.5f,  0.0f, 1.0f, 0.0f,   1.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  0.0f, 0.0f, 1.0f,   1.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  0.0f, 0.0f, 1.0f,   1.0f, 1.0f,
            -0.5f,  0.5f, -0.5f,  1.0f, 1.0f, 0.0f,   0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f, 0.0f,   0.0f, 0.0f,
            -0.5f, -0.5f,  0.5f,  1.0f, 0.0f, 1.0f,   0.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  0.0f, 1.0f, 1.0f,   1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,   1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,   1.0f, 1.0f,
            -0.5f,  0.5f,  0.5f,  0.5f, 0.5f, 0.5f,   0.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  1.0f, 0.0f, 1.0f,   0.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  1.0f, 0.5f, 0.0f,   1.0f, 0.0f,
            -0.5f,  0.5f, -0.5f,  0.5f, 0.0f, 1.0f,   1.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.2f, 0.8f, 0.2f,   0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.2f, 0.8f, 0.2f,   0.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  0.0f, 0.7f, 0.9f,   0.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  1.0f, 0.5f, 0.0f,   1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.8f, 0.3f, 0.3f,   1.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  0.3f, 0.8f, 0.3f,   1.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.3f, 0.3f, 0.8f,   0.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.3f, 0.3f, 0.8f,   0.0f, 1.0f,
            0.5f, -0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   0.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.8f, 0.3f, 0.3f,   1.0f, 0.0f,
            -0.5f, -0.5f, -0.5f,  0.6f, 0.1f, 0.1f,   0.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.1f, 0.6f, 0.1f,   1.0f, 1.0f,
            0.5f, -0.5f,  0.5f,  0.1f, 0.1f, 0.6f,   1.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  0.1f, 0.1f, 0.6f,   1.0f, 0.0f,
            -0.5f, -0.5f,  0.5f,  0.7f, 0.2f, 0.7f,   0.0f, 0.0f,
            -0.5f, -0.5f, -0.5f,  0.6f, 0.1f, 0.1f,   0.0f, 1.0f,
            -0.5f,  0.5f, -0.5f,  0.2f, 0.9f, 0.9f,   0.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  0.9f, 0.2f, 0.9f,   1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   1.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  0.2f, 0.9f, 0.2f,   0.0f, 0.0f,
            -0.5f,  0.5f, -0.5f,  0.2f, 0.9f, 0.9f,   0.0f, 1.0f
    };
```

At this point I had... [a reaction](https://www.youtube.com/watch?v=umDr0mPuyQc).

Probably a reaction a lot of you had seeing this for the first time. My dear readers, this is a cube. Specifically, the 
vertices of a cube. The first 3 floats are a Vertex position, the next 3 are colour data, and the last 2 are texture
coordinates. This said, the way it presented is frankly horrible. I cannot tell where one faces ends, another begins, it
isn't clear what each row represents. 

This is one of these rare times where I will comment my code, if only for understanding. I am a believer (sometimes to a
fault) in the idea of "Clean Code", the idea that having lots of comments in your code is not preferable to the code being
self-explanatory. Due to that, outside of learning, I try to keep commenting to a minimum where possible. That said, whilst
learning, I tend to the opposite.

I have a very strict "wake up tomorrow" policy when it comes to my note-taking. "If I am going to struggle to remember 
what I wrote here is tomorrow, I need to make better notes". In this case, a 36 line monstrosity of an Array is something
I am almost guaranteed to forget by the next day. Hence, I added minimal, but crucial comments to the array, and the one
you can see in the actual project looks like this:

```java
    float[] cubeVertices = {
            // Each of these sets of vertices represent a different face of the cube, with EBOs not being used.

            // back face
            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f, 0.0f,   0.0f, 0.0f,
            0.5f, -0.5f, -0.5f,  0.0f, 1.0f, 0.0f,   1.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  0.0f, 0.0f, 1.0f,   1.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  0.0f, 0.0f, 1.0f,   1.0f, 1.0f,
            -0.5f,  0.5f, -0.5f,  1.0f, 1.0f, 0.0f,   0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f, 0.0f,   0.0f, 0.0f,

            // front face
            -0.5f, -0.5f,  0.5f,  1.0f, 0.0f, 1.0f,   0.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  0.0f, 1.0f, 1.0f,   1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,   1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,   1.0f, 1.0f,
            -0.5f,  0.5f,  0.5f,  0.5f, 0.5f, 0.5f,   0.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  1.0f, 0.0f, 1.0f,   0.0f, 0.0f,

            // left face
            -0.5f,  0.5f,  0.5f,  1.0f, 0.5f, 0.0f,   1.0f, 0.0f,
            -0.5f,  0.5f, -0.5f,  0.5f, 0.0f, 1.0f,   1.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.2f, 0.8f, 0.2f,   0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.2f, 0.8f, 0.2f,   0.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  0.0f, 0.7f, 0.9f,   0.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  1.0f, 0.5f, 0.0f,   1.0f, 0.0f,

            // right face
            0.5f,  0.5f,  0.5f,  0.8f, 0.3f, 0.3f,   1.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  0.3f, 0.8f, 0.3f,   1.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.3f, 0.3f, 0.8f,   0.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.3f, 0.3f, 0.8f,   0.0f, 1.0f,
            0.5f, -0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   0.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.8f, 0.3f, 0.3f,   1.0f, 0.0f,

            // bottom face
            -0.5f, -0.5f, -0.5f,  0.6f, 0.1f, 0.1f,   0.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.1f, 0.6f, 0.1f,   1.0f, 1.0f,
            0.5f, -0.5f,  0.5f,  0.1f, 0.1f, 0.6f,   1.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  0.1f, 0.1f, 0.6f,   1.0f, 0.0f,
            -0.5f, -0.5f,  0.5f,  0.7f, 0.2f, 0.7f,   0.0f, 0.0f,
            -0.5f, -0.5f, -0.5f,  0.6f, 0.1f, 0.1f,   0.0f, 1.0f,

            // top face
            -0.5f,  0.5f, -0.5f,  0.2f, 0.9f, 0.9f,   0.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  0.9f, 0.2f, 0.9f,   1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   1.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  0.2f, 0.9f, 0.2f,   0.0f, 0.0f,
            -0.5f,  0.5f, -0.5f,  0.2f, 0.9f, 0.9f,   0.0f, 1.0f
    };
```
I did not explain the position, colour, and texture coordinates, as I was already quite confident in them. I was confident as,
when writing this section of the log, I was able to explain each float value from memory, without looking it up. If I was 
not able to do so, I would have almost certainly added said notes to the array. In fact, early into the project I did,
whilst I was still getting to grips with the concept. 

However, sometimes notes just simply need to be there. Whilst I know that each set of 6 vertices represents a cube face,
being able to parse out those groups of 6 rows, when they are all together in one 36 line array, is not a nice experience.
Hence, the comments remain as, just because you understand a concept, does not mean it isn't difficult to realise it is 
something you understand at first glance. 

To conclude, there will never will there be a time I look at the uncommented version of this array, and be able to separate 
each face's vertices in my head. Hence, there will never be a time when I will be confident enough to remove those comments. 

## Finally, a use for Matrices!
Once I had got my head around the cube Vertices, the abstractions disused in the previous section actually helped me quite
a bit. That is because, as each Vertex still followed the float structure of the previous Triangle (position, colour, texture)
I could essentially "plug and play" the cube vertices array into my already written Shader. 

But I realised the headache was fast approaching when I first encountered a flat "square" upon first rendering. This was 
due to the view being directly facing forward, hence I was unable to see the other sides of the cube. Hence, I was moving
into the horrid concept that is Transformations and Camera logic. Luckily though, as I began to read the "Transformations"
section of the LearnOpenGL content, I found I was looking at an old friend. 

80% of the chapter was going through the basics of **Vector Calculus and Matrices**. To be blunt, I have written exams about
these subjects, they are concepts I am very familiar with. As such, I of course skipped the entire chapter and... yeah, no. 
Of course I read the whole thing haha! This goes back to what I discussed in my last log, you should not assume you know
everything, and should at least verify first. 

Indeed, whilst there was little I did not already know, the context provided through the section did actually help me slowly
understand how I could utilise these concepts to solve the transformation problem. Using just a few simple matrices, literally
a couple of lines of code, I could transform the rendered model, no matter how complex, with relative ease. After having 
a bit of a play with automatically rotating the cube based on simulation run time, I decided to start tackling the next
big task.

## Cameras and Input Callbacks
The Matrices for the Camera were, surprisingly, quite intuitive and relatively easy to set up. Only a few core concepts
needed to be understood (quite literally 3 different types of matrix). However, there was a pet peeve of mine as an educator
that occured during this section, one that vindicates my habit of not writing code until I have read the full tutorial content.

The LearnOpenGL chapter for cameras discusses the following 3 Matrices: 
- Position Matrix: Representing where the camera is within the world space
- Direction Matrix: Represents what position the camera is pointed at
- Right Axis Matrix: Represents the axis that is considered to be the cameras local "x-axis"

The concepts themselves were fine. But annoyingly the concepts came with code examples! "But Jordan, why are code examples
of the concepts you are learning a bad thing?" I hear you ask. They are a bad thing as they are immediately abandoned after 
being explained. Why? Because there is an in-built function for it instead, that uses slightly different matrices. These 
concepts do come back later in other ways, and ultimately were used as part of my input logic for the camera, but the initial
code taught was functionally useless to me, and I ended up deleting it entirely.

Sometimes just explaining the core concept is enough, without forcing the user to write lines of code they will end up
never using. It is important, as a learner, to realise that quite a few creators of educational content do not take that
lesson to heart, and it is up to you to separate the useless from the useful worked examples. 

## Calling back to Input Callbacks
Some may recall from earlier logs, I decided to bin off all the content to do with "Input Callbacks" as, whilst fancy, it
did not necessarily help me with my initial learning. Almost to vindicate me, this is the first concept in my learning
journey where Input callbacks were actually relevant. 

This is because, whilst being able to represent a camera's position / direction in the world space is great, it would be
nice if the values for that position / direction were capable of being changed. As such, I needed a way to, at run time
tell the program to dynamically change the Camera's values based on inputs from my keyboard and mouse. Being able to do 
**call back** to code that should only trigger when certain **input** occurs... i.e. Input Callbacks! 

These callback could be called 2 ways. Either directly at any time, or triggered on a successful key press:

Triggered when pressed: 
```java        
    glfwSetCursorPosCallback(windowId, (windowHandle, xpos, ypos) -> { //Some code here});
```

Or called directly to see what is happening with a specific key: 

```java
    glfwGetKey(window.getWindowId(), GLFW_KEY_S);
```

I ended up trying both methods for the input to change my camera, but found that using the callback led to very jumpy / 
laggy movement. This was due to when the callbacks would occur (after the main logic had looped during each frame). I 
investigated online and found that the callbacks should really only be used for things such as UI elements (pressing buttons
for example). 

## Poor Naming = Poor Practice

Whilst learning camera logic, I actually made a mistake I was not happy with. Specifically, when naming of the fields 
regarding the rotation of the camera due to cursor movements. Two of the fields I have in the `Camera.Java` class are:

```java
    private float yaw = -90.0f;
    private float pitch = 0.0f;
```

I actually consider this a poor practice (yes, even with all my preaching, I sometimes do not practice it myself) that I 
will be correcting in a future branch. Why is it poor practice? Because I measure both yaw and pitch in degrees. That practice
in itself is not a bad thing, but it is when I have not specified. It is important to contextualise what a field is supposed 
to be. Too many times have I seen someone work with Radians when it was intended to be in degrees (and vice versa). 

More appropriate names (and feel free to hold me to account on this in a month's time) would be something similar to:

```java
    private float yawDegrees = -90.0f;
    private float pitchDegrees = 0.0f;
```

No documentation required, no verbose "this value needs to be set in degrees" elaboration within the Javadoc comments. 
Just a clear and concise way to tell the user "yep, these need to be in degrees". 

## Final Thoughts
This marks the end of me learning the absolute basics of OpenGL, with me now able to render 3d objects, and move around
the scene I render them in, at runtime. I had been quite worried about this stage of the learning. However, after a few 
mistakes and a particularly nasty vertices array, I was able to get to a reasonably nice setup, along with being confident
in a lot of the primary concepts of OpenGL. 

I think the things that made this stage relatively nice can be explained by how I approached it. I did not relent on the
rules of thumb I spoke of in previous logs, and (more or less) kept my approach to the learning consistent. 

Specifically:

- Ensuring concepts I know will be difficult to remember are commented clearly and concisely.
- Making sure to read sections entirely before trying to implement them.
- Ensuring I approach each section with a mindset of "I do not know what I do not know", even if the subject seems to be one I'm familiar with (like matrices).
- Looking back at "finished work" and thinking "What could I have done better" (such as the poorly named `pitch` and `yaw` fields)

As with the last chapter, I could have merely followed the tutorial content, and got a working model. Instead,
I took time to confirm I understood individual concepts. Ultimately, I came away from my learning with enough knowledge to 
set this sort of project up again, without having to constantly rely on the tutorial content for aid. 

Furthermore, I also have a good idea of what I want to learn next. The LearnOpenGL content becomes quite fluid after this
point, allowing users to more freely learn whatever individual concepts they wish. If you get to this point, and you are
a bit clueless about what you want to learn next, it might be worth revising the concepts of the basics section of the 
learning content. If you do not know what you want to learn next, there is a good chance the concepts you have already 
studied have not been fully understood by yourself.

It has been a nice experience learning OpenGL, the first (almost) fully novel subject I have decided to learn in quite
some time. And, as I have understood it well enough, I am excited to sink my teeth into the more complex concepts of 
the API. I hope you can come away from reading these initial logs with some new approaches / rules of thumb that
might help you have a similar experience to mine when learning new things. 



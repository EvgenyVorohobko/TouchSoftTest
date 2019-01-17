const arrayDiscs = document.getElementById("disc_wait");
const rightDiscs = document.getElementById("right_disc");
const leftDiscs = document.getElementById("left_disc");
const button = document.getElementById("button");
const input = document.getElementById("input");
const error = document.getElementById("error");
const message = document.getElementById("message");

let left = 0;
let right = 0;

button.addEventListener("click", clickOnButton);

//the method shows the equality between left and right the rod
function checkTheEquality() {
    if (left === right && left !== 0) {
        message.innerHTML = "Rod is ready!";
    } else {
        message.innerHTML = "TouchSoft";
    }
}

//the method checks rod mass
function validateEnteredWeight(value) {
    return value > 0 && value <= 20 && value !== "";
}

//the method displays information about work
function clickOnButton() {
    if (validateEnteredWeight(parseFloat(input.value))) {
        error.hidden = true;
        addWeightToArray(input.value);
    } else {
        error.hidden = false;
    }
}
//add discs to the array
function addWeightToArray(value) {
    let child = document.createElement('div');
    child.innerHTML = value;
    child.classList.add('elementOfDisc');
    child.id = 'disc_first' + Date.now();
    arrayDiscs.appendChild(child);
}

//add discs to the right
arrayDiscs.addEventListener("contextmenu", function (event) {
    let target = event.target;
    if (target.parentElement.className !== 'disc_wait') {
        return;
    }

    right += parseInt(target.innerHTML);
    addWeightToRod(target, "right", rightDiscs);
    arrayDiscs.children[target.id].remove();

    checkTheEquality();
});

//add discs to the left
arrayDiscs.addEventListener("click", function (event) {
    let target = event.target;
    if (target.parentElement.className !== 'disc_wait') return;

    left += parseInt(target.innerHTML);
    addWeightToRod(target, "left", leftDiscs);
    arrayDiscs.children[target.id].remove();

    checkTheEquality();
});

//remove discs to the right
rightDiscs.addEventListener("click", function (event) {
    let target = event.target;
    if (target.parentElement.className !== 'disc_on_rod') return;

    right -= parseInt(target.innerHTML);
    addWeightToArray(target.innerHTML);
    rightDiscs.children[target.id].remove();

    checkTheEquality();
});

//remove discs to the left
leftDiscs.addEventListener("click", function (event) {
    let target = event.target;
    if (target.parentElement.className !== 'disc_on_rod') return;

    left -= parseInt(target.innerHTML);
    addWeightToArray(target.innerHTML);
    leftDiscs.children[target.id].remove();

    checkTheEquality();
});

//add discs to the rod
function addWeightToRod(target, location, locSide) {
    let child = document.createElement('div');
    child.innerHTML = target.innerHTML;
    child.classList.add('elementOfDisc');
    child.id = location + 'disc_second' + Date.now();
    locSide.appendChild(child);
}
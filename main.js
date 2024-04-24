for (let i = 1; i < 100; i++) {
    printLine(i)
}

function printLine(text) {
    let output = document.getElementById("output");
    let newLine = document.createElement("p");
    newLine.textContent = (output.childElementCount + 1) + ") " + text;
    output.appendChild(newLine)
}

function deleteLine(line) {
    let output = document.getElementById("output");
    if (line > output.childElementCount) {
        alert("Веди себя нормально, а то че ты, как это!")
    }
    let child = output.childNodes[line]
    output.removeChild(child);
}

async function boom() {
    let boomGif = document.createElement("img");
    boomGif.src = "boom.gif";
    boomGif.id = "boomGif";

    let body = document.getElementsByTagName("body")[0];
    body.appendChild(boomGif);

    await new Promise(r => setTimeout(r, 1000));
    let output = document.getElementById("output");
    output.replaceChildren();
    console.log(output.children)

    await new Promise(r => setTimeout(r, 1000));
    body.removeChild(boomGif);
}

document.getElementById("addNewLine").addEventListener("click", function () {
    printLine(prompt("Введите текст, чтобы вывести текст в вывод"));
});
document.getElementById("deleteLine").addEventListener("click", function () {
    line = prompt("Введите номер удаляемой строки, которая будет удалена");
    deleteLine(isNaN(line) ? document.getElementById("output").childElementCount : line);
});
document.getElementById("boom").addEventListener("click", function () {
    boom();
});
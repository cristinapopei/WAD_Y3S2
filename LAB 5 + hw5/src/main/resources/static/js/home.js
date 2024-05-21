document.addEventListener('DOMContentLoaded', function() {
    populateSelectElements();
});

function populateSelectElements() {
    fetch('/api/v1/products/')
        .then(response => response.json())
        .then(data => {
            const deleteSelect = document.getElementById("deleteProductId");
            const modifySelect = document.getElementById("modifyProductId");

            data.forEach(product => {
                const option = document.createElement('option');
                option.value = product.id;
                option.text = product.id;
                deleteSelect.appendChild(option.cloneNode(true));
                modifySelect.appendChild(option.cloneNode(true));
            });

            modifySelect.addEventListener('change', () => {
                console.log("Modify Product ID Selected:", modifySelect.value);
                showProductProperties(modifySelect.value);
            });
        });
}

function showProductProperties(productId) {
    fetch(`/api/v1/products/${productId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(product => {
            console.log("Product Details:", product);
            const propertiesDiv = document.getElementById("productProperties");
            propertiesDiv.innerHTML = `ID: ${product.id}<br>Name: ${product.name}<br>Price: ${product.price}`;
        })
        .catch(error => {
            console.error('Error fetching product details:', error);
        });
}


const prodDiv = document.getElementById("products");
const addProductButton = document.getElementById("addProductButton");

refreshProducts();

function refreshProducts(){
    fetch('/api/v1/products/')
        .then(response => response.json())
        .then(data => showProducts(data));
}

function showProducts(products){
    // Also, useful to see difference between imperative programming in Vanilla JS vs declarative in REACT
    prodDiv.innerHTML="";
    for(let i = 0; i< products.length; i++ ){
        let addedProd = document.createElement("div");
        addedProd.innerHTML = `${products[i].id} ${products[i].name} ${products[i].price}`
        prodDiv.appendChild(addedProd);
    }
}

addProductButton.onclick = () => {
    const productName = document.getElementById("productName").value;
    const productPrice = document.getElementById("productPrice").value;
    const product = {
        name: productName,
        price: productPrice
    };

    fetch('/api/v1/products/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                console.error('Error adding product:', response.status);
            }
        });
};


const deleteProductButton = document.getElementById("deleteProductButton");
deleteProductButton.onclick = () => {
    const productId = document.getElementById("deleteProductId").value;

    fetch(`/api/v1/products/${productId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                location.reload();
                alert('Product deleted successfully.');
            } else {
                console.error('Error deleting product:', response.status);
                alert('Error deleting product. Please try again.');
            }
        });
};

const modifyProductButton = document.getElementById("modifyProductButton");
modifyProductButton.onclick = () => {
    const productId = document.getElementById("modifyProductId").value;
    const productName = document.getElementById("modifyProductName").value;
    const productPrice = document.getElementById("modifyProductPrice").value;
    const product = {
        name: productName,
        price: productPrice
    };

    fetch(`/api/v1/products/${productId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                console.error('Error modifying product:', response.status);
            }
        });
};

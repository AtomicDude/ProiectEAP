<script>

    function debounce(fn, duration) {
        var timer;
        return function(){
            clearTimeout(timer);
            timer = setTimeout(fn, duration);
        }
    }

    function search()
    {
        let search_input = document.getElementById("search_input");
        let search_output = document.getElementById("search_output");

        var http = new XMLHttpRequest();
        http.onreadystatechange = function()
        {
            if (this.readyState === 4 && this.status === 200) {
                search_output.innerHTML = this.responseText;
            }
        };

        http.open("GET", "<%=request.getContextPath() + "/lists/search_list.jsp?search_key="%>" + search_input.value, true);
        http.send();
    }

    var onSearch = debounce(search, 500);

</script>
<input class = "searchbar" type = "text" id = "search_input" placeholder = "Search..." autocomplete = "off" oninput = "onSearch()" onfocusout = "this.value = ''; onSearch()">
<div id = "search_output" class = "ddown">
    <!-- search list updated by XMLHttpRequest -->
</div>

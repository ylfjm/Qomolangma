<div>
    <button id="showDialog">showDialog</button>
</div>
<script>
    $(function() {
        $("#showDialog").click(function(){
            addWindow('dialogDiv', 'showDialog', [ 500, 300 ], dialogOnHidden, dialogOnShown, -200);
        });
    });
    function dialogOnHidden() {
        
    }
    function dialogOnShown() {
        
    }
</script>

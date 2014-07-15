        $('.value').html($('input').val());

                $('input').change(function(){
            var cur = $('input').val();
            $(this).attr('value', cur );
            $('.value').html(cur);
        });
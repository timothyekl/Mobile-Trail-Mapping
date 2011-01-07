#import "InterestPoint.h"

@implementation InterestPoint

@synthesize id = _id;
@synthesize location = _location;
@synthesize category = _category;
@synthesize title = _title;
@synthesize summary = _summary;
@synthesize color = _color;
@synthesize categoryID = _categoryID;

-(id) initWithParams:(NSInteger)id 
    location:(CGPoint)p category:(NSString *)c title:(NSString *)t summary:(NSString *)s {
        
	self = [super init];
	if (self != nil) {
        _id = id;
        _location = p;
        _category = c;
        _title = t;
        _summary = s;
        _categoryID = -1;
        _color = [UIColor redColor];
	}
	return self;
}

- (void) dealloc {
    
    [_category release];
    [_title release];
    [_summary release];
    [_color release];
	[super dealloc];
}

@end
